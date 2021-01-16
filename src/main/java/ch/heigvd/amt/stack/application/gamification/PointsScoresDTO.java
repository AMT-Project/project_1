package ch.heigvd.amt.stack.application.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class PointsScoresDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class PointsScoreDTO {
        private String pointScaleName;
        private int score;
    }

    @Singular
    private List<PointsScoreDTO> pointsScores;
}