package ch.heigvd.amt.stack.application.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class LeaderboardsDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class LeaderboardDTO {
        private PointScalesDTO.PointScaleDTO pointScale;
        private LeaderboardEntriesDTO leaderboardEntries;
    }

    @Singular
    private List<LeaderboardDTO> leaderboards;
}