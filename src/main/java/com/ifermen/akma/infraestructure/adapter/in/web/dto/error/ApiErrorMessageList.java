package com.ifermen.akma.infraestructure.adapter.in.web.dto.error;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiErrorMessageList {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<String> messages;
    private String path;
    private String method;
}
