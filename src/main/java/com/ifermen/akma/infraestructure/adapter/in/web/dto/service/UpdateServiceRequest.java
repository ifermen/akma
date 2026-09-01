package com.ifermen.akma.infraestructure.adapter.in.web.dto.service;

import com.ifermen.akma.infraestructure.adapter.in.web.validation.TrimmedLength;
import lombok.Data;

@Data
public class UpdateServiceRequest {
    @TrimmedLength(min = 3,message = "name: 'String must be between 3 and 100 chars.'")
    private String name;
    @TrimmedLength(max = 255, message = "description: 'String must be between 0 and 255 chars.'")
    private String description;
}
