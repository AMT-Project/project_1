package ch.heigvd.amt.stack.application.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class PointScalesDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class PointScaleDTO {
        private String pointScaleName;
        private String pointScaleDesc;
    }

    @Singular
    private List<PointScaleDTO> pointScales;
}
