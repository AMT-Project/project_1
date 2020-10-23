package ch.heigvd.amt.stack.domain.question.comment;

import ch.heigvd.amt.stack.domain.Id;

import java.util.UUID;

public class CommentId extends Id {
    public CommentId() {
        super();
    }

    public CommentId(String id) {
        super(id);
    }

    public CommentId(UUID id) {
        super(id);
    }
}
