package ch.heigvd.amt.stack.application.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class BadgesDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class BadgeDTO {
        private String badgeName;
        private String badgeDesc;
    }

    @Singular
    private List<BadgeDTO> badges;
}
