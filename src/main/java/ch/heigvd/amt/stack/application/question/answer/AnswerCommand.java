package ch.heigvd.amt.stack.application.question.answer;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AnswerCommand {
    @Builder.Default
    private PersonId authorUUID = null;

    @Builder.Default
    private QuestionId questionUUID = null;

    @Builder.Default
    private String content = "No content";
}

