package ch.heigvd.amt.stack.application.question;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode
public class SubmitQuestionCommand {
    @Builder.Default
    private QuestionId questionUUID = null;

    @Builder.Default
    private PersonId authorUUID = null;

    @Builder.Default
    private String title = "No title";

    @Builder.Default
    private String text = "No content";

    @Builder.Default
    private LocalDate createdOn = null;
}
