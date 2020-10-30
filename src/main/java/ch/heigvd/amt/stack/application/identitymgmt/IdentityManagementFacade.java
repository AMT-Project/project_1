package ch.heigvd.amt.stack.application.identitymgmt;

import ch.heigvd.amt.stack.application.identitymgmt.authenticate.*;
import ch.heigvd.amt.stack.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stack.application.identitymgmt.login.RegistrationFailedException;
import ch.heigvd.amt.stack.application.identitymgmt.profile.UpdateUserCommand;
import ch.heigvd.amt.stack.application.identitymgmt.profile.UpdateUserFailedException;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import org.mindrot.jbcrypt.BCrypt;

public class IdentityManagementFacade {

    private IPersonRepository personRepository;

    public IdentityManagementFacade(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

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
            .uuid(person.getUuid())
            .username(person.getUsername())
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .email(person.getEmail())
            .build();

        return currentUser;
    }

    public CurrentUserDTO update(UpdateUserCommand updateUserCommand) throws UpdateUserFailedException {
        // Get old user data
        Person currentUser = personRepository.findById(updateUserCommand.getUuid())
            .orElseThrow(() -> new UpdateUserFailedException("Couldn't find user to edit"));

        // Check for email uniqueness
        if(!currentUser.getEmail().equals(updateUserCommand.getNewEmail())) {
            if(personRepository.findByEmail(updateUserCommand.getNewEmail()).orElse(null) != null) {
                throw new UpdateUserFailedException("Email already in use");
            }
        }

        boolean changePassword = false;
        // Check if password change is needed
        if(!updateUserCommand.getOldPasswordClear().isEmpty()) {
            if(!BCrypt.checkpw(updateUserCommand.getOldPasswordClear(), currentUser.getEncryptedPassword())) {
                throw new UpdateUserFailedException("Old password doesn't match");
            }
            changePassword = true;
        }

        // Updating the user
        Person updatedUser = null;
        try {
            if(changePassword) {
                updatedUser = Person.builder()
                    .uuid(currentUser.getUuid())
                    .username(updateUserCommand.getUsername())
                    .email(updateUserCommand.getNewEmail())
                    .firstName(updateUserCommand.getNewFirstname())
                    .lastName(updateUserCommand.getNewLastname())
                    .clearTextPassword(updateUserCommand.getNewPasswordClear())
                    .build();
            } else {
                updatedUser = Person.builder()
                    .uuid(currentUser.getUuid())
                    .username(updateUserCommand.getUsername())
                    .email(updateUserCommand.getNewEmail())
                    .firstName(updateUserCommand.getNewFirstname())
                    .lastName(updateUserCommand.getNewLastname())
                    .encryptedPassword(currentUser.getEncryptedPassword())
                    .build();
            }
        } catch(Exception e) {
            throw new UpdateUserFailedException(e.getMessage());
        }

        // Update user and return user data
        if(personRepository.update(updatedUser) == 1) {
            return CurrentUserDTO.builder()
                .uuid(currentUser.getUuid())
                .username(updateUserCommand.getUsername())
                .email(updateUserCommand.getNewEmail())
                .firstName(updateUserCommand.getNewFirstname())
                .lastName(updateUserCommand.getNewLastname())
                .build();
        } else {
            return CurrentUserDTO.builder()
                .uuid(currentUser.getUuid())
                .username(currentUser.getUsername())
                .email(currentUser.getEmail())
                .firstName(currentUser.getFirstName())
                .lastName(currentUser.getLastName())
                .build();
        }
    }

    public int countUsers() {
        return this.personRepository.count();
    }
}
