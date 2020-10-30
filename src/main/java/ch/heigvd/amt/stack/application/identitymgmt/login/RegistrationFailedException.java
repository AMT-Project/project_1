package ch.heigvd.amt.stack.application.identitymgmt.login;

import ch.heigvd.amt.stack.application.BusinessException;
import lombok.Value;

@Value
public class RegistrationFailedException extends BusinessException {
    public RegistrationFailedException(String message) {
        super(message);
    }
}
