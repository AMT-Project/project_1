package ch.heigvd.amt.stack.application.identitymgmt.authenticate;

import ch.heigvd.amt.stack.domain.person.PersonId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private PersonId uuid;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
