package ch.heigvd.amt.stack.application.question.answer;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class AnswersDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class AnswerDTO {
        private PersonId authorUUID;
        private QuestionId questionId;
        private String content;
    }

    @Singular
    private List<AnswerDTO> answers;
}
