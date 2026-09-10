package com.ifermen.akma.application.usecase.key;

import com.ifermen.akma.application.command.key.CreateKeyCommand;
import com.ifermen.akma.application.exception.NotFoundException;
import com.ifermen.akma.application.port.in.key.CreateKeyUseCase;
import com.ifermen.akma.application.port.out.repository.KeyRepository;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private KeyRepository keyRepository;

    //TODO: If already exist key with same service and user, revoke the old row
    @Override
    public KeyModel execute(CreateKeyCommand createKeyCommand){

        ServiceModel service = this.serviceRepository.findById(createKeyCommand.getServiceId());
        List<PermissionModel> permissions = findPermissions(createKeyCommand.getPermissions());

        checkPermissionsFromService(service,permissions);

        String secret = createSecret();
        String key = addBrand(secret,createKeyCommand.getName());
        String secretPrefix = getPrefix(secret);
        String keyPrefix = addBrand(secretPrefix,createKeyCommand.getName());
        String keyHash = hash(key);

        KeyModel keyModel = KeyModel.builder()
                .name(createKeyCommand.getName())
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

    //TODO: Change "akma" with a service identifier
    private String addBrand(String secret, String name){
        return "akma_" + name + "_" + secret;
    }

    private String getPrefix(String key){
        return key.substring(0,10);
    }

    private String hash(String secret){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(secret.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of().formatHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
