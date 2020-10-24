package ch.heigvd.amt.stack.application.identitymgmt;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stack.domain.person.PersonId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;

class IdentityManagementFacadeTest {

    // test exception: https://howtodoinjava.com/junit5/expected-exception-example/
    @Test
    void registerThrowsExceptionWhenDuplicateUserWithSameUsername() {
        try{
            ServiceRegistry serviceRegistry = (ServiceRegistry)
                    new InitialContext().lookup("ServiceRegistry");
            RegisterCommand registerCommand = RegisterCommand.builder()
                    .uuid(new PersonId())
                    .username("Toto")
                    .firstName("Marcel")
                    .lastName("Dupont")
                    .email("marcel.dupont@example.com")
                    .clearTextPassword("pwd")
                    .build();
            RegisterCommand registerCommandDuplicate = RegisterCommand.builder()
                    .uuid(new PersonId())
                    .username("Toto")
                    .firstName("Marcel2")
                    .lastName("Dupont2")
                    .email("marcel2.dupont2@example.com")
                    .clearTextPassword("pwd2")
                    .build();
            IdentityManagementFacade identityManagementFacade = serviceRegistry
                    .getIdentityManagementFacade();
            identityManagementFacade.register(registerCommand);
            Assertions.assertThrows(RegistrationFailedException.class, () -> {
                identityManagementFacade.register(registerCommandDuplicate);
            });
        } catch (RegistrationFailedException | NamingException e) {
            e.printStackTrace();
        }
    }
}