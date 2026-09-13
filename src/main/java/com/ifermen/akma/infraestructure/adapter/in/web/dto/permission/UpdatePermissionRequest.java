package com.ifermen.akma.infraestructure.adapter.in.web.dto.permission;

import com.ifermen.akma.infraestructure.adapter.in.web.validation.AllowedValues;
import com.ifermen.akma.infraestructure.adapter.in.web.validation.TrimmedLength;
import lombok.Data;

@Data
public class UpdatePermissionRequest {

    @TrimmedLength(min = 3,message = "target: 'String must be between 3 and 100 chars.'")
    private String url;
    @AllowedValues(values = {"GET","POST","PUT","DELETE"},
            ignoreCase = true,
            message = "method: 'must be one of the allowed values (GET,POST,PUT,DELETE)'")
    private String method;
    @TrimmedLength(max = 255, message = "description: 'String must be between 0 and 255 chars.'")
    private String description;
}
