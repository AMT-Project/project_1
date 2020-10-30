package ch.heigvd.amt.stack.application.question.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class VotesDTO {
    private int count;
}
