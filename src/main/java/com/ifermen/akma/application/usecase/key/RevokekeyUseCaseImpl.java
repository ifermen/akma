package com.ifermen.akma.application.usecase.key;

import com.ifermen.akma.application.dto.command.key.RevokeKeyCommand;
import com.ifermen.akma.application.exception.NotFoundException;
import com.ifermen.akma.application.port.in.key.RevokekeyUseCase;
import com.ifermen.akma.application.port.out.repository.KeyRepository;
import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.domain.service.ApiKeyFormatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RevokekeyUseCaseImpl implements RevokekeyUseCase {

    private KeyRepository keyRepository;
    private ApiKeyFormatService apiKeyFormatService;

    @Override
    public void execute(RevokeKeyCommand revokeKeyCommand){

        String prefix = this.apiKeyFormatService.getPrefix(revokeKeyCommand.getKey());

        List<KeyModel> candidates = this.keyRepository.findByPrefix(prefix);
        KeyModel match = this.apiKeyFormatService.findMatch(candidates, revokeKeyCommand.getKey());
        if(match == null){
            throw new NotFoundException("Key not found");
        }

        match.setRevokeAt(LocalDateTime.now());

        this.keyRepository.update(match);
    }
}
