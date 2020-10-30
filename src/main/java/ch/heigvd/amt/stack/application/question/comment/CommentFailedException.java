package ch.heigvd.amt.stack.application.question.comment;

import ch.heigvd.amt.stack.application.BusinessException;
import lombok.Value;

@Value
public class CommentFailedException extends BusinessException {
    CommentFailedException(String message) {
        super(message);
    }
}
