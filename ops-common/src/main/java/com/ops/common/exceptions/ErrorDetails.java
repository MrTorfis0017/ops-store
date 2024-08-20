package com.ops.common.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ErrorDetails {

    private final HttpStatus httpStatus;

    private final String message;

    private final String description;

    public static ErrorDetails ofWithDescription(OpsErrorCode errorCode, Object... description){
        return new ErrorDetails(errorCode.getHttpStatus(),errorCode.getMessage(),errorCode.getDescription(description));
    }
}
