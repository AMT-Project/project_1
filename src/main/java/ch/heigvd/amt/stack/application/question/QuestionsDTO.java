package ch.heigvd.amt.stack.application.question;

import ch.heigvd.amt.stack.domain.person.PersonId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class QuestionsDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO {
        private PersonId author;
        private String title;
        private String description;
    }

    @Singular
    private List<QuestionDTO> questions;
}
