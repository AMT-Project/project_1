package ch.heigvd.amt.stack.application.question;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class QuestionsDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO {
        private QuestionId uuid;
        private PersonId authorUUID;
        private String username;
        private String title;
        private String description;
        private LocalDate createdOn;

    }

    @Singular
    private List<QuestionDTO> questions;
}
