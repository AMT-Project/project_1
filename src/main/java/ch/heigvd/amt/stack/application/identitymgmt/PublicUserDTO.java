package ch.heigvd.amt.stack.application.identitymgmt;

import ch.heigvd.amt.stack.domain.person.PersonId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PublicUserDTO {
    private String username;
    private PersonId personId;
}
