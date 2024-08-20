package com.ops.auth.controllers;

import com.ops.common.exceptions.ErrorDetails;
import com.ops.common.exceptions.OpsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(OpsException.class)
    public ResponseEntity<ErrorDetails> adminException(OpsException e) {
        log.error("AdminException", e);
        return ResponseEntity
                .status(e.getErrorDetails().getHttpStatus().value())
                .body(e.getErrorDetails());
    }
}
