package com.ifermen.akma.application.port.in.key;

import com.ifermen.akma.application.command.key.CreateKeyCommand;
import com.ifermen.akma.domain.model.KeyModel;

public interface CreateKeyUseCase {
    KeyModel execute(CreateKeyCommand createKeyCommand);
}
