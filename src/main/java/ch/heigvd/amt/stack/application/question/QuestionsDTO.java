package ch.heigvd.amt.stack.application.question;

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
    public static class QuestionDTO{
        private String author;
        private String text;
    }

    @Singular
    private List<QuestionDTO> questions;
}
