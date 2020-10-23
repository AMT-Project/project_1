package ch.heigvd.amt.stack.application.question.comment;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class CommentsDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class CommentDTO {
        private PersonId authorUUID;
        private QuestionId questionId;
        private AnswerId answerId;
        private String content;
    }

    @Singular
    private List<ch.heigvd.amt.stack.application.question.comment.CommentsDTO.CommentDTO> comments;
}
