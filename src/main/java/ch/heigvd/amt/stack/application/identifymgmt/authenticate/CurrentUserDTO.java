package ch.heigvd.amt.stack.application.identifymgmt.authenticate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private String username;
    private String firstname;
    private String lastName;
    private String email;

}
