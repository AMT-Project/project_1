package ch.heigvd.amt.stack.application.question.answer;

import ch.heigvd.amt.stack.domain.person.PersonId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AnswersQuery {

    @Builder.Default
    private PersonId authorUUID = null;
}
