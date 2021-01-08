//package ch.heigvd.amt.stack.application;
//
//import okhttp3.Request;
//import okhttp3.Response;
//
//import okhttp3.OkHttpClient;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import javax.enterprise.context.ApplicationScoped;
//import java.io.IOException;
//import java.util.Objects;
//
//@ApplicationScoped
//class Application {
//    // TODO connect to the gamification engine on startup
//    //  startup: https://stackoverflow.com/a/44923402
//    //  okhttp: https://square.github.io/okhttp/
//
//    static private String gamificationAppAuthKey;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() {
//        // FIXME doesn't show up in "docker logs <id of spring app>"
//        System.out.println("hello world, I have just started up");
//        System.err.println("hello world, I have just started up");
//        // "localhost:8080/applications"
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("localhost:8080/applications")
//                .build();
//
//        try (Response response = client.newCall(request).execute()){
//            gamificationAppAuthKey = Objects.requireNonNull(response.body()).string();
//            System.exit(0); // TODO remove
//            System.out.println(gamificationAppAuthKey);
//        } catch (Error | IOException e){
//            System.err.println(e.getMessage());
//            System.exit(1); // TODO remove
//        }
//    }
//}
//
//// try 2
//
//@Component
//class ApplicationStartup
//        implements ApplicationListener<ApplicationReadyEvent> {
//    // note: intellij says it's unused
//
//    static private String gamificationAppAuthKey;
//
//    /**
//     * This event is executed as late as conceivably possible to indicate that
//     * the application is ready to service requests.
//     */
//    @Override
//    public void onApplicationEvent(final ApplicationReadyEvent event) {
//        // FIXME doesn't show up in "docker logs <id of spring app>"
//        System.out.println("hello world, I have just started up");
//        System.err.println("hello world, I have just started up");
//        // "localhost:8080/applications"
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("localhost:8080/applications")
//                .build();
//
//        try (Response response = client.newCall(request).execute()){
//            gamificationAppAuthKey = Objects.requireNonNull(response.body()).string();
//            System.exit(0); // TODO remove
//            System.out.println(gamificationAppAuthKey);
//        } catch (Error | IOException e){
//            System.err.println(e.getMessage());
//            System.exit(1); // TODO remove
//        }
//
//        return;
//    }
//}
//
//// try 3
//
//@Component
//class ServerInitializer implements ApplicationRunner {
//
//    static private String gamificationAppAuthKey;
//
//    @Override
//    public void run(ApplicationArguments applicationArguments) throws Exception {
//        // FIXME doesn't show up in "docker logs <id of spring app>"
//        System.out.println("hello world, I have just started up");
//        System.err.println("hello world, I have just started up");
//        // "localhost:8080/applications"
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("localhost:8080/applications")
//                .build();
//
//        try (Response response = client.newCall(request).execute()){
//            gamificationAppAuthKey = Objects.requireNonNull(response.body()).string();
//            System.exit(0); // TODO remove
//            System.out.println(gamificationAppAuthKey);
//        } catch (Error | IOException e){
//            System.err.println(e.getMessage());
//            System.exit(1); // TODO remove
//        }
//
//    }
//}
