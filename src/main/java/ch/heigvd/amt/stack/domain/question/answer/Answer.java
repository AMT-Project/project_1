package ch.heigvd.amt.stack.domain.question.answer;

import ch.heigvd.amt.stack.domain.IEntity;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.Question;
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
    private AnswerId id = new AnswerId();
    private String content;
    private QuestionId questionUUID;
    private PersonId personUUID;
    private LocalDateTime created_at;

    @Override
    public AnswerId getId() {
        return this.id;
    }

    @Override
    public Answer deepClone() {
        return this.toBuilder().id(new AnswerId(id.asString())).build();
    }

    public static class AnswerBuilder {
        public Answer build() {
            id = new AnswerId();
            content = "";

            return new Answer(id, content, questionUUID, personUUID, created_at);
        }
    }
}
