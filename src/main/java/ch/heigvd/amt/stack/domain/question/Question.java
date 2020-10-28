package ch.heigvd.amt.stack.domain.question;

import ch.heigvd.amt.stack.domain.IEntity;
import ch.heigvd.amt.stack.domain.person.PersonId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)

public class Question implements IEntity<Question, QuestionId> {
    private QuestionId id = new QuestionId();
    private PersonId authorUUID;
    private String title;
    private String description;

    @Override
    public Question deepClone() {
        return this.toBuilder()
            .id(new QuestionId(id.asString()))
            .build();
    }

    public static class QuestionBuilder {
        public Question build() {
            if(id == null) {
                id = new QuestionId();
            }
            if(authorUUID == null) {
                throw new IllegalArgumentException("Author is mandatory"); // TODO : appliquer sur les autres champs
            }
            if(title == null) {
                throw new IllegalArgumentException("Title is mandatory");
            }
            if(description == null) {
                throw new IllegalArgumentException("Description is mandatory");
            }
            return new Question(id, authorUUID, title, description);
        }
    }
}
