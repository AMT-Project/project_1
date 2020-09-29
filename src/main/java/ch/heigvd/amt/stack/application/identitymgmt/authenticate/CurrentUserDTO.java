package ch.heigvd.amt.stack.application.identitymgmt.authenticate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
