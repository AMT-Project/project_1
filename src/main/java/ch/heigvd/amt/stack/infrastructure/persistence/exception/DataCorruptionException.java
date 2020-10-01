package ch.heigvd.amt.stack.infrastructure.persistence.exception;

import lombok.Value;

@Value
public class DataCorruptionException extends RuntimeException {
    private String message;
}
