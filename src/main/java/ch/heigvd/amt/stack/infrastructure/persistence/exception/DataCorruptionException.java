package ch.heigvd.amt.stack.infrastructure.persistence.exception;

import ch.heigvd.amt.stack.application.BusinessException;
import lombok.Value;

@Value
public class DataCorruptionException extends RuntimeException {
    private String message;
}
