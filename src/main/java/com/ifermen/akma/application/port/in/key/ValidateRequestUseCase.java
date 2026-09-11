package com.ifermen.akma.application.port.in.key;

import com.ifermen.akma.application.dto.query.ValidateRequestQuery;
import com.ifermen.akma.application.dto.result.ValidateRequestResult;

import java.util.UUID;

public interface ValidateRequestUseCase {
    ValidateRequestResult execute(ValidateRequestQuery validateRequestQuery);
}
