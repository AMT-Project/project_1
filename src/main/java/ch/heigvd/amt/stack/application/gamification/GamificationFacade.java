package ch.heigvd.amt.stack.application.gamification;

import ch.heigvd.amt.stack.domain.person.PersonId;
import okhttp3.*;
import org.json.JSONArray;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class GamificationFacade {

    private final String appAuthKey = System.getenv("APP_KEY");

    public String getAppAuthKey() {
        return appAuthKey;
    }

    private final String backendUrl = System.getenv("BACKEND_URL");

    public String getBackendUrl() {
        return backendUrl;
    }

    public void postParticipationEvent(PersonId personId, String eventType) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Input
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        // Conversion: https://mincong.io/2017/02/16/convert-date-to-string-in-java/
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String time = sdf.format(date);

        // json body formatting validation: https://jsonformatter.curiousconcept.com/#
        String bodyString =
            "{\"appUserId\":\"" + personId.asString() + "\","
                + "\"eventType\":\"" + eventType + "\","
                + "\"timestamp\":\"" + time + "\""
                + "}";
        RequestBody body = RequestBody.create(JSON, bodyString);

        Request httpRequest = new Request.Builder()
            .post(body)
            .url(backendUrl + "/events")
            .header("X-API-KEY", appAuthKey)
            .build();
        try(Response httpResponse = client.newCall(httpRequest).execute()) {
            if(!httpResponse.isSuccessful()) throw new IOException("Unexpected code " + httpResponse);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getLeaderboard(String pointScale, int limit) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        Request httpRequest = new Request.Builder()
            .get()
            .url(backendUrl + "/leaderboards/" + pointScale + "?limit=" + limit)
            .header("X-API-KEY", appAuthKey)
            .build();

        JSONArray Jarray = null;

        try(Response httpResponse = client.newCall(httpRequest).execute()) {
            if(!httpResponse.isSuccessful())
                return Jarray;
            String responseBody = httpResponse.body().string();

            Jarray = new JSONArray(responseBody);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return Jarray;
    }

    public JSONArray getPointScales() {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        Request httpRequest = new Request.Builder()
            .get()
            .url(backendUrl + "/pointScales")
            .header("X-API-KEY", appAuthKey)
            .build();

        JSONArray Jarray = null;

        try(Response httpResponse = client.newCall(httpRequest).execute()) {
            if(!httpResponse.isSuccessful())
                return Jarray;
            String responseBody = httpResponse.body().string();

            Jarray = new JSONArray(responseBody);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return Jarray;
    }

    public JSONArray getUserBadges(String appUserId) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        Request httpRequest = new Request.Builder()
            .get()
            .url(backendUrl + "/users/" + appUserId + "/badges")
            .header("X-API-KEY", appAuthKey)
            .build();

        JSONArray Jarray = null;

        try(Response httpResponse = client.newCall(httpRequest).execute()) {
            if(!httpResponse.isSuccessful())
                return Jarray;
            String responseBody = httpResponse.body().string();

            Jarray = new JSONArray(responseBody);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return Jarray;
    }

    public JSONArray getUserPointsScores(String appUserId) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        Request httpRequest = new Request.Builder()
            .get()
            .url(backendUrl + "/users/" + appUserId + "/scores")
            .header("X-API-KEY", appAuthKey)
            .build();

        JSONArray Jarray = null;

        try(Response httpResponse = client.newCall(httpRequest).execute()) {
            if(!httpResponse.isSuccessful())
                return Jarray;
            String responseBody = httpResponse.body().string();

            Jarray = new JSONArray(responseBody);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return Jarray;
    }
}
