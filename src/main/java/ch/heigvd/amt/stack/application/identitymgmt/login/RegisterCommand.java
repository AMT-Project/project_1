package ch.heigvd.amt.stack.application.identitymgmt.login;

import ch.heigvd.amt.stack.domain.person.PersonId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCommand {
    private PersonId uuid = new PersonId();
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String clearTextPassword;
}
