package com.ifermen.akma.application.dto.query;

import lombok.Data;

import java.util.UUID;

@Data
public class ValidateRequestQuery {

    private UUID serviceId;

    private String env;

    private String url;

    private String method;

    private String key;
}
