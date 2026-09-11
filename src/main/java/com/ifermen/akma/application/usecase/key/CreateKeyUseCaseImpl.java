package com.ifermen.akma.application.usecase.key;

import com.ifermen.akma.application.dto.command.key.CreateKeyCommand;
import com.ifermen.akma.application.exception.NotFoundException;
import com.ifermen.akma.application.port.in.key.CreateKeyUseCase;
import com.ifermen.akma.application.port.out.repository.KeyRepository;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.application.port.out.service.BCryptHashingService;
import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateKeyUseCaseImpl implements CreateKeyUseCase {

    private ServiceRepository serviceRepository;
    private PermissionRepository permissionRepository;
    private BCryptHashingService bCryptHashingService;
    private KeyRepository keyRepository;

    //TODO: If already exist a key with same service, env and user, revoke the old one
    @Override
    public KeyModel execute(CreateKeyCommand createKeyCommand){

        ServiceModel service = this.serviceRepository.findById(createKeyCommand.getServiceId());
        List<PermissionModel> permissions = findPermissions(createKeyCommand.getPermissions());

        checkPermissionsFromService(service,permissions);

        String env = createKeyCommand.getEnv().toUpperCase().trim();

        String brand = generateBrand(service.getAcronym(),env);

        String secret = createSecret();

        String secretPrefix = getSecretPrefix(secret);
        String keyPrefix = brand + secretPrefix;

        String key = brand + secret;
        String keyHash = this.bCryptHashingService.hash(key);

        KeyModel keyModel = KeyModel.builder()
                .env(env)
                .userId(createKeyCommand.getUserId())
                .service(service)
                .keyHash(keyHash)
                .keyPrefix(keyPrefix)
                .createdAt(LocalDateTime.now())
                .build();

        keyModel.generateRandomId();

        KeyModel created = this.keyRepository.create(keyModel);

        KeyModel createdWithPermission = this.keyRepository.addPermissions(created,permissions);
        createdWithPermission.setKey(key);

        return createdWithPermission;
    }

    private List<PermissionModel> findPermissions(List<UUID> permissionIds){
        return permissionIds.stream().map(permissionRepository::findById).toList();
    }

    private void checkPermissionsFromService(ServiceModel service, List<PermissionModel> permissions){
        for (PermissionModel permission : permissions) {
            if (!permission.getService().getId().equals(service.getId())) {
                throw new NotFoundException("Permission not found");
            }
        }
    }

    private String createSecret(){
        SecureRandom secureRandom = new SecureRandom();

        byte[] secretByte = new byte[32];
        secureRandom.nextBytes(secretByte);

        return HexFormat.of().formatHex(secretByte);
    }

    private String generateBrand(String serviceAcronym, String env){
        return serviceAcronym + "_" + env + "_";
    }

    private String getSecretPrefix(String secret){
        return secret.substring(0,10);
    }
}
