package ch.heigvd.amt.stack.application.question.answer;

import ch.heigvd.amt.stack.application.BusinessException;
import lombok.Value;

@Value
public class AnswerFailedException extends BusinessException {
    public AnswerFailedException(String message) {
        super(message);
    }
}
