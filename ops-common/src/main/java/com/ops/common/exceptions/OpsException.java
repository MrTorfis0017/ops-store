package com.ops.common.exceptions;

import lombok.Getter;

@Getter
public class OpsException extends RuntimeException {

    private final ErrorDetails errorDetails;

    private OpsException(ErrorDetails errorDetails, Exception cause) {
        super(createMessage(errorDetails), cause);
        this.errorDetails = errorDetails;
    }

    private OpsException(ErrorDetails errorDetails) {
        this(errorDetails, null);
    }

    public static OpsException errorWithDescription(OpsErrorCode errorCode, Object... description) {
        return new OpsException(ErrorDetails.ofWithDescription(errorCode, description));
    }

    private static String createMessage(ErrorDetails errorDetails) {
        if (errorDetails != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(errorDetails.getMessage()).append(": ");
            if (errorDetails.getDescription() != null) {
                stringBuilder.append(errorDetails.getDescription());
            } else {
                stringBuilder.append("no description");
            }
            return stringBuilder.toString();
        }
        return "";
    }
}