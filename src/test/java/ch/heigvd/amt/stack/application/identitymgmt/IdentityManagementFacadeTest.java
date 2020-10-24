package ch.heigvd.amt.stack.application.identitymgmt;

import ch.heigvd.amt.stack.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stack.domain.person.PersonId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;


class IdentityManagementFacadeTest {

    static SeContainerInitializer initializer;

    @BeforeAll
    public static void setupCdi(){
        //  FIXME java.lang.IllegalStateException: No valid CDI implementation found
        initializer = SeContainerInitializer.newInstance();
    }

    // test exception: https://howtodoinjava.com/junit5/expected-exception-example/
    @Test
    void registerThrowsExceptionWhenDuplicateUserWithSameUsername() {
        try(SeContainer container = initializer.initialize()){
            IdentityManagementFacade identityManagementFacade =
                    container.select(IdentityManagementFacade.class).get();
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
            identityManagementFacade.register(registerCommand);
            Assertions.assertThrows(RegistrationFailedException.class, () -> {
                identityManagementFacade.register(registerCommandDuplicate);
            });
        } catch (RegistrationFailedException e) {
            e.printStackTrace();
        }
    }
}