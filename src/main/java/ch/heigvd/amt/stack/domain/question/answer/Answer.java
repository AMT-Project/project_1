package ch.heigvd.amt.stack.domain.question.answer;

import ch.heigvd.amt.stack.domain.IEntity;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Answer implements IEntity<Answer, AnswerId> {
    private AnswerId uuid = new AnswerId();
    private String content;
    private QuestionId questionUUID;
    private PersonId authorUUID;
    private LocalDateTime createdOn;

    @Override
    public Answer deepClone() {
        return this.toBuilder().uuid(new AnswerId(uuid.asString())).build();
    }

    public static class AnswerBuilder {
        public Answer build() {
            if(uuid == null) {
                uuid = new AnswerId();
            }
            if(content == null || content.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Content is mandatory");
            }
            if(questionUUID == null) {
                throw new java.lang.IllegalArgumentException("QuestionUUID is mandatory");
            }
            if(authorUUID == null) {
                throw new java.lang.IllegalArgumentException("PersonUUID is mandatory");
            }
            if(createdOn == null) {
                createdOn = LocalDateTime.now();
            }

            return new Answer(uuid, content, questionUUID, authorUUID, createdOn);
        }
    }
}
