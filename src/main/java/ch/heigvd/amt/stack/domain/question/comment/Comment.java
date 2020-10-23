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
    private AnswerId answerIdUUID;
    private LocalDateTime created_at;

    @Override
    public CommentId getId() {
        return null;
    }

    @Override
    public Comment deepClone() { return this.toBuilder().id(new CommentId(id.asString())).build();}

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
            if(questionUUID == null) {
                throw new java.lang.IllegalArgumentException("QuestionUUID is mandatory");
            }
            if(answerIdUUID == null) {
                throw new java.lang.IllegalArgumentException("AnswerUUID is mandatory");
            }
            if(created_at == null) {
                created_at = null;
                //throw new java.lang.IllegalArgumentException("Creation date/time is mandatory");
            }

            return new Comment(id, content, personUUID, questionUUID, answerIdUUID , created_at);
        }
    }
}
