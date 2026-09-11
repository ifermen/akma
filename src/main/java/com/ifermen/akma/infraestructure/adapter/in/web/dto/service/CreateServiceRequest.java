package com.ifermen.akma.infraestructure.adapter.in.web.dto.service;

import com.ifermen.akma.infraestructure.adapter.in.web.validation.TrimmedLength;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateServiceRequest {
    @TrimmedLength(min = 3,message = "name: 'String must be between 3 and 100 chars.'")
    @NotBlank(message = "name: 'String must not be blank.'")
    private String name;

    @TrimmedLength(min = 3, max = 3,message = "acronym: 'String must be 3 chars.'")
    @NotBlank(message = "acronym: 'String must not be blank.'")
    private String acronym;

    @TrimmedLength(max = 255, message = "description: 'String must be between 0 and 255 chars.'")
    private String description;
}
