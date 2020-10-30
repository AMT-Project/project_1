package ch.heigvd.amt.stack.application.question.answer;

import ch.heigvd.amt.stack.application.question.comment.CommentsDTO;
import ch.heigvd.amt.stack.application.question.vote.VotesDTO;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class AnswersDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class AnswerDTO {
        private AnswerId uuid;
        private String content;
        private QuestionId questionUUID;
        private PersonId authorUUID;
        private String username;
        private LocalDateTime createdOn;

        private CommentsDTO comments;
        private VotesDTO votes;

        public String printLocalDateTime (){
            //Get current date time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy @ HH:mm");

            return this.createdOn.format(formatter);
        }
    }

    @Singular
    private List<AnswerDTO> answers;
}
