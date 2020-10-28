package ch.heigvd.amt.stack.application.question.comment;

import ch.heigvd.amt.stack.domain.person.PersonId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class CommentsQuery {

    @Builder.Default
    private PersonId authorUUID = null;
}
