package ch.heigvd.amt.stack.domain.question.comment;

import ch.heigvd.amt.stack.domain.IEntity;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.Answer;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)

public class Comment implements IEntity<Comment, CommentId> {
    private CommentId uuid = new CommentId();
    private String content;
    private PersonId authorUUID;
    private QuestionId questionUUID;
    private AnswerId answerUUID;
    private LocalDateTime createdOn;

    @Override
    public CommentId getUuid() {
        return this.uuid;
    }

    @Override
    public Comment deepClone() {
        return this.toBuilder().uuid(new CommentId(uuid.asString())).build();
    }

    public static class CommentBuilder {
        public Comment build() {
            if(uuid == null) {
               uuid = new CommentId();
            }
            if(content == null || content.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Content is mandatory");
            }
            if(authorUUID == null) {
                throw new java.lang.IllegalArgumentException("authorUUID is mandatory");
            }
            if(createdOn == null) {
                createdOn = LocalDateTime.now();
            }
            return new Comment(uuid, content, authorUUID, questionUUID, answerUUID, createdOn);
        }
    }
}
