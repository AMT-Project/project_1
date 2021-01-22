package ch.heigvd.amt.stack.application.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class LeaderboardEntriesDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class LeaderboardEntryDTO {
        private String username;
        private int pointsSum;
    }

    @Singular
    private List<LeaderboardEntryDTO> leaderboardEntries;
}
