package ch.heigvd.amt.stack.application.gamification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Objects;

public class GamificationFacade {
    private String appAuthKey = "";


    public String getAppAuthKey() {

        System.out.println(appAuthKey);

        if (appAuthKey.equals("")) {
            // FIXME body formatted

            var client = HttpClient.newHttpClient();
            var values = new HashMap<String, String>() {{
                put("name", "Stack");
            }};
            var objectMapper = new ObjectMapper();
            try {
                String requestBody = objectMapper
                        .writeValueAsString(values);
                // create a request
                var request = HttpRequest.newBuilder(
                        URI.create("http://api:8080/applications"))
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

                // use the client to send the request
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // the response:
                System.out.println(response);
                System.out.println(response.body());
                System.out.println(response.headers());
                System.out.println(response.headers().firstValue("x-api-key"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // TODO Choose
//            OkHttpClient client = new OkHttpClient();
//            RequestBody formBody = new FormBody.Builder()
//                    .add("name", "stack")
//                    .build();
//            System.out.println(client);
//            System.out.println(formBody);
//
//            Request request = new Request.Builder()
//                    .method("POST", formBody)
//                    .url("http://localhost:8080/applications")
//                    .build();
//            System.out.println(request);
//            try (Response response = client.newCall(request).execute()){
//                System.out.println(response); // FIXME error "Failed to connect to localhost/127.0.0.1:8080"
//                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//                appAuthKey = Objects.requireNonNull(response.body()).string();
//                System.out.println("\n\n\n\n"+appAuthKey);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        return appAuthKey;
    }

    public void getUserPointScale(){

    }
}
