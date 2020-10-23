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
    private CommentId id;
    private String content;
    private PersonId personUUID;
    private QuestionId questionUUID;
    private AnswerId answerUUID;
    private LocalDateTime createdOn;

    @Override
    public CommentId getId() {
        return this.id;
    }

    @Override
    public Comment deepClone() {
        return this.toBuilder().id(new CommentId(id.asString())).build();
    }

    public static class CommentBuilder {
        public Comment build() {
            if(id == null) {
                id = new CommentId();
            }
            if(content == null || content.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Content is mandatory");
            }
            if(personUUID == null) {
                throw new java.lang.IllegalArgumentException("PersonUUID is mandatory");
            }
            if(createdOn == null) {
                createdOn = LocalDateTime.now();
            }
            return new Comment(id, content, personUUID, questionUUID, answerUUID, createdOn);
        }
    }
}
