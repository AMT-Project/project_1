package ch.heigvd.amt.stack.application.identifymgmt.authenticate;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AuthenticateCommand {
    public String username;
    public String clearTextPassword;
}
