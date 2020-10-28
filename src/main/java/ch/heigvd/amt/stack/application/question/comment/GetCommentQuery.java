package ch.heigvd.amt.stack.application.question.comment;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class GetCommentQuery {
    private QuestionId uuid;

    @Builder.Default
    private PersonId currentUser = null;
}
