package ch.heigvd.amt.stack.application.identitymgmt.authenticate;

import ch.heigvd.amt.stack.domain.person.PersonId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class PersonsDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class PersonDTO {
        private String uuid;
        private String username;
    }

    @Singular
    private List<PersonsDTO.PersonDTO> persons;
}
