package com.ifermen.akma.infraestructure.adapter.out.security;

import com.ifermen.akma.application.port.out.service.BCryptHashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptHashingServiceImpl implements BCryptHashingService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String hash(String secret){
        return this.encoder.encode(secret);
    }

    @Override
    public boolean matches(String secret, String hash){
        return encoder.matches(secret,hash);
    }
}
