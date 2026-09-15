package com.ifermen.akma.domain.service;

public interface BCryptHashingService {
    String hash(String secret);

    boolean matches(String secret, String hash);
}
