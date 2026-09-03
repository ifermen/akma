package com.ifermen.akma.infraestructure.adapter.in.web.dto.permission;

import com.ifermen.akma.infraestructure.adapter.in.web.validation.AllowedValues;
import com.ifermen.akma.infraestructure.adapter.in.web.validation.TrimmedLength;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePermissionRequest {

    @TrimmedLength(min = 3,message = "target: 'String must be between 3 and 100 chars.'")
    @NotBlank(message = "target: 'String must not be blank.'")
    private String target;
    @AllowedValues(values = {"READ","CREATE","UPDATE","DELETE"},
            ignoreCase = true,
            message = "privilege: 'must be one of the allowed values'")
    @NotBlank(message = "privilege: 'String must not be blank.'")
    private String privilege;
    @TrimmedLength(max = 255, message = "description: 'String must be between 0 and 255 chars.'")
    private String description;
}
