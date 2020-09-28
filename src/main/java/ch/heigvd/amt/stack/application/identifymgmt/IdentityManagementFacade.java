package ch.heigvd.amt.stack.application.identifymgmt;

import ch.heigvd.amt.stack.application.identifymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.stack.application.identifymgmt.authenticate.AuthenticationFailedException;
import ch.heigvd.amt.stack.application.identifymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;

public class IdentityManagementFacade {

    private IPersonRepository personRepository;

    public CurrentUserDTO authenticate(AuthenticateCommand command) throws AuthenticationFailedException {
        Person person = personRepository.findByUsername(command.getUsername())
            .orElseThrow(() -> new AuthenticationFailedException("User not found"));

        boolean success = person.authenticate(command.getClearTextPassword());
        if(!success) {
            throw new AuthenticationFailedException("Credentials verification failed");
        }

        CurrentUserDTO currentUser = CurrentUserDTO.builder()
            .username(person.getUsername())
            .firstname(person.getFirstName())
            .lastName(person.getLastName())
            .email(person.getEmail())
            .build();

        return currentUser;
    }

}
