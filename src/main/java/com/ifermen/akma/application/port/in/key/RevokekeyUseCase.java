package com.ifermen.akma.application.port.in.key;

import com.ifermen.akma.application.dto.command.key.RevokeKeyCommand;

public interface RevokekeyUseCase {
    void execute(RevokeKeyCommand revokeKeyCommand);
}
