package ch.heigvd.amt.stack.application.question.comment;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import ch.heigvd.amt.stack.domain.question.comment.CommentId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class CommentsDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class CommentDTO {
        private CommentId uuid;
        private PersonId authorUUID;
        private String username;
        private QuestionId questionUUID;
        private AnswerId answerUUID;
        private LocalDateTime createdOn;
        private String content;
    }

    @Singular
    private List<CommentDTO> comments;
}
