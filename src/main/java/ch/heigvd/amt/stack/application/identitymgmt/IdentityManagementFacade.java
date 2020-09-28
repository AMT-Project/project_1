package ch.heigvd.amt.stack.application.identitymgmt;

import ch.heigvd.amt.stack.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.AuthenticationFailedException;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;

public class IdentityManagementFacade {
    private IPersonRepository personRepository;

    public IdentityManagementFacade(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // TODO ADD EXCEPTION !
    // I'm not able to add a the same exception than the podcast...
    public void register(RegisterCommand command) throws RegistrationFailedException {
        Person existingPersonWithSameUsername = personRepository.findByUsername(command.getUsername()).orElse(null);

        if(existingPersonWithSameUsername != null) {
            throw new RegistrationFailedException("Username is already used");
        }
        try {
            Person newPerson = Person.builder()
                .username(command.getUsername())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .clearTextPassword(command.getClearTextPassword())
                .build();

            personRepository.save(newPerson);
        } catch(Exception e) {
            throw new RegistrationFailedException(e.getMessage());
        }
    }

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
