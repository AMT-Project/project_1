package ch.heigvd.amt.stack.application.identitymgmt.profile;

import ch.heigvd.amt.stack.application.BusinessException;

public class UpdateUserFailedException extends BusinessException {
    public UpdateUserFailedException(String message) {
        super(message);
    }
}
