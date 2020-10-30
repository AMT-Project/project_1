package ch.heigvd.amt.stack.application.question.vote;

import ch.heigvd.amt.stack.application.BusinessException;
import lombok.Value;

@Value
public class VoteFailedException extends BusinessException {
    public VoteFailedException(String message) {
        super(message);
    }
}
