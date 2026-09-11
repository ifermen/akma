package com.ifermen.akma.application.port.out.service;

public interface BCryptHashingService {
    String hash(String secret);

    boolean matches(String secret, String hash);
}
