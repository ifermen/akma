package com.ifermen.akma.application.usecase.key;

import com.ifermen.akma.application.dto.query.ValidateRequestQuery;
import com.ifermen.akma.application.dto.result.ValidateRequestResult;
import com.ifermen.akma.application.exception.BadRequestException;
import com.ifermen.akma.application.exception.NotFoundException;
import com.ifermen.akma.application.port.in.key.ValidateRequestUseCase;
import com.ifermen.akma.application.port.out.repository.KeyRepository;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.service.ApiKeyFormatService;
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
    private ApiKeyFormatService apiKeyFormatService;

    @Override
    public ValidateRequestResult execute(ValidateRequestQuery validateRequestQuery){

        ServiceModel serviceModel = this.serviceRepository.findById(validateRequestQuery.getServiceId());

        if(!serviceModel.getAcronym().equals(this.apiKeyFormatService.getService(validateRequestQuery.getKey()))){
            throw new BadRequestException("Key not for this service");
        }

        String env = validateRequestQuery.getEnv().toUpperCase().trim();
        if(!env.equals(this.apiKeyFormatService.getEnv(validateRequestQuery.getKey()))){
            throw new BadRequestException("Key not for this env");
        }

        PermissionModel permission =
                this.permissionRepository.findPermissionFromServiceByUrlAndMethod(
                        validateRequestQuery.getServiceId(),
                        validateRequestQuery.getUrl(),
                        validateRequestQuery.getMethod()
                );

        String prefix = this.apiKeyFormatService.getPrefix(validateRequestQuery.getKey());
        List<KeyModel> candidates = this.keyRepository.findByPrefix(prefix);
        KeyModel match = this.apiKeyFormatService.findMatch(candidates, validateRequestQuery.getKey());
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
}
