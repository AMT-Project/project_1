package ch.heigvd.amt.stack.application.identitymgmt.profile;

import ch.heigvd.amt.stack.domain.person.PersonId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class UpdateUserCommand {
    private PersonId uuid;
    private String username;
    private String newEmail;
    private String oldEmail;
    private String newFirstname;
    private String newLastname;
    private String oldPasswordClear;
    private String newPasswordClear;
}
