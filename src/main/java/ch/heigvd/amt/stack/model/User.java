package ch.heigvd.amt.stack.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
