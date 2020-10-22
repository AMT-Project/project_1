package ch.heigvd.amt.stack.domain.question.answer;

import ch.heigvd.amt.stack.domain.IEntity;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Answer implements IEntity<Answer, AnswerId> {
    private AnswerId id;
    private PersonId authorUUID;
    private QuestionId questionId;
    private String content;

    @Override
    public AnswerId getId() {
        return id;
    }

    @Override
    public Answer deepClone() {
        return this.toBuilder().
                id(new AnswerId(id.asString())).
                build();
    }

    public static class AnswerBuilder {
        public Answer build() {
            if(id == null) {
                id = new AnswerId();
            }
            if(authorUUID == null) {
                authorUUID = null;
            }
            if(questionId == null) {
                questionId = null;
            }
            if(content == null) {
                content = "nullDesc";
            }
            return new Answer(id, authorUUID, questionId, content);
        }
    }

}
