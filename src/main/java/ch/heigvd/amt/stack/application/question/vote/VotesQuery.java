package ch.heigvd.amt.stack.application.question.vote;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class VotesQuery {
    @Builder.Default
    private PersonId authorUUID = null;

    @Builder.Default
    private QuestionId questionUUID = null;

    @Builder.Default
    private AnswerId answerUUID = null;
}
