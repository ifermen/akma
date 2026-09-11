package com.ifermen.akma.infraestructure.adapter.in.web.dto.key;

import com.ifermen.akma.infraestructure.adapter.in.web.validation.AllowedValues;
import com.ifermen.akma.infraestructure.adapter.in.web.validation.TrimmedLength;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ValidateKeyRequest {

    @NotBlank(message = "url: 'String must not be blank'")
    private String url;

    @TrimmedLength(min = 3, max = 3, message = "env: 'String must be 3 chars.'")
    @NotBlank(message = "env: 'String must not be blank'")
    private String env;

    @AllowedValues(values = {"GET","POST","PUT","DELETE"},
            ignoreCase = true,
            message = "method: 'String must be one of the allowed values (GET,POST,PUT,DELETE)'")
    @NotBlank(message = "method: 'String must not be blank.'")
    private String method;

    @NotBlank(message = "key: 'String must not be blank.'")
    private String key;
}
