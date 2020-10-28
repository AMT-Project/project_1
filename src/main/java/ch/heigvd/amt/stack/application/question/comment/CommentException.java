package ch.heigvd.amt.stack.application.question.comment;

import ch.heigvd.amt.stack.application.BusinessException;

public class CommentException extends BusinessException {
    CommentException(String message) {
        super(message);
    }
}
