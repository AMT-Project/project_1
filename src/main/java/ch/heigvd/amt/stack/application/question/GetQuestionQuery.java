package ch.heigvd.amt.stack.application.question;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class GetQuestionQuery {
    private QuestionId uuid;

    @Builder.Default
    private PersonId currentUser = null;
}
