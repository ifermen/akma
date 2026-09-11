package com.ifermen.akma.application.usecase.key;

import com.ifermen.akma.application.dto.query.ValidateRequestQuery;
import com.ifermen.akma.application.dto.result.ValidateRequestResult;
import com.ifermen.akma.application.exception.BadRequestException;
import com.ifermen.akma.application.exception.NotFoundException;
import com.ifermen.akma.application.port.in.key.ValidateRequestUseCase;
import com.ifermen.akma.application.port.out.repository.KeyRepository;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.application.port.out.service.BCryptHashingService;
import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ValidateKeyUseCaseImpl implements ValidateRequestUseCase {

    private KeyRepository keyRepository;
    private PermissionRepository permissionRepository;
    private ServiceRepository serviceRepository;
    private BCryptHashingService bCryptHashingService;

    @Override
    public ValidateRequestResult execute(ValidateRequestQuery validateRequestQuery){

        ServiceModel serviceModel = this.serviceRepository.findById(validateRequestQuery.getServiceId());

        if(!serviceModel.getAcronym().equals(getService(validateRequestQuery.getKey()))){
            throw new BadRequestException("Key not for this service");
        }

        String env = validateRequestQuery.getEnv().toUpperCase().trim();
        if(!env.equals(getEnv(validateRequestQuery.getKey()))){
            throw new BadRequestException("Key not for this env");
        }

        PermissionModel permission =
                this.permissionRepository.findPermissionFromServiceByUrlAndMethod(
                        validateRequestQuery.getServiceId(),
                        validateRequestQuery.getUrl(),
                        validateRequestQuery.getMethod()
                );

        String prefix = getPrefix(validateRequestQuery.getKey());
        List<KeyModel> candidates = this.keyRepository.findByPrefix(prefix);
        KeyModel match = findMatch(candidates, validateRequestQuery.getKey());
        if(match == null){
            throw new NotFoundException("Key not found");
        }

        boolean valid = match.getKeyPermissions()
                .stream()
                .anyMatch(kp -> kp
                        .getPermission().equals(permission)
                );

        return ValidateRequestResult
                .builder()
                .valid(valid)
                .userId(match.getUserId())
                .build();
    }

    private String getPrefix(String key){
        String[] fragments = key.split("_");

        return fragments[0] + "_" + fragments[1] + "_" + fragments[2].substring(0,10);
    }

    private String getService(String key){
        String[] fragments = key.split("_");

        return fragments[0];
    }

    private String getEnv(String key){
        String[] fragments = key.split("_");

        return fragments[1];
    }

    private KeyModel findMatch(List<KeyModel> candidates,String key){
        KeyModel match = null;
        for (int i = 0; i < candidates.size() && match == null; i++) {
            KeyModel candidate = candidates.get(i);
            if(bCryptHashingService.matches(key,candidate.getKeyHash())){
                match = candidate;
            }
        }
        return match;
    }
}
