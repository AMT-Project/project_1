package ch.heigvd.amt.stack.application.identitymgmt.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCommand {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String clearTextPassword;
}
