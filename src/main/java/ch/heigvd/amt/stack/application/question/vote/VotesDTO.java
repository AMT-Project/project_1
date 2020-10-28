package ch.heigvd.amt.stack.application.question.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@Builder
@Getter
@EqualsAndHashCode
public class VotesDTO {
    private int count;
}
