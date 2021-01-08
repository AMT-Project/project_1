package ch.heigvd.amt.stack.application.gamification;

public class GamificationFacade {

    private final String appAuthKey = System.getenv("APP_KEY");

    public String getAppAuthKey() {
        return appAuthKey;
    }

    private final String backendUrl = System.getenv("BACKEND_URL");

    public String getBackendUrl() {
        return backendUrl;
    }
}
