package ch.heigvd.amt.stack.application.question;

import ch.heigvd.amt.stack.application.BusinessException;
import lombok.Value;

@Value
public class QuestionFailedException extends BusinessException {
    public QuestionFailedException(String message) {
        super(message);
    }
}
