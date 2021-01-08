package ch.heigvd.amt.stack.application.gamification;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class GamificationFacade {
    private String appAuthKey = "";


    public String getAppAuthKey() {

        if (appAuthKey.equals("")) {
            // TODO make call

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://80.218.235.185:8080/applications")
                    .build();
            try (Response response = client.newCall(request).execute()){
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                appAuthKey = Objects.requireNonNull(response.body()).string();
                System.out.println("\n\n\n\n"+appAuthKey);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return appAuthKey;
    }

    public void getUserPointScale(){

    }
}
