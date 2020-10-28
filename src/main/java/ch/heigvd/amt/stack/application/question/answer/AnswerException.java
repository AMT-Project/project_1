package ch.heigvd.amt.stack.application.question.answer;

import ch.heigvd.amt.stack.application.BusinessException;

public class AnswerException extends BusinessException {
    public AnswerException(String message) {
        super(message);
    }
}
