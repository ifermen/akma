package com.ifermen.akma.domain.service;

import com.ifermen.akma.domain.model.KeyModel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ApiKeyFormatService {

    private BCryptHashingService bCryptHashingService;

    public KeyModel findMatch(List<KeyModel> candidates,String key){
        KeyModel match = null;
        for (int i = 0; i < candidates.size() && match == null; i++) {
            KeyModel candidate = candidates.get(i);
            if(bCryptHashingService.matches(key,candidate.getKeyHash())){
                match = candidate;
            }
        }
        return match;
    }

    public String getPrefix(String key){
        String[] fragments = key.split("_");

        return fragments[0] + "_" + fragments[1] + "_"  + fragments[2].substring(0,10);
    }

    public String getService(String key){
        String[] fragments = key.split("_");

        return fragments[0];
    }

    public String getEnv(String key){
        String[] fragments = key.split("_");

        return fragments[1];
    }
}