package ch.heigvd.amt.stack.application.identitymgmt.authenticate;

import ch.heigvd.amt.stack.application.BusinessException;
import lombok.Value;

@Value
public class AuthenticationFailedException extends BusinessException {

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
