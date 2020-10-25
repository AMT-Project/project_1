package ch.heigvd.amt.stack.application.identitymgmt;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.AuthenticationFailedException;
import ch.heigvd.amt.stack.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stack.domain.person.PersonId;
import org.junit.Assert;
import org.junit.internal.runners.statements.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

class IdentityManagementFacadeTest {

    // 1
    static SeContainerInitializer initializer;

    @BeforeAll
    public static void setupCdi(){
        initializer = SeContainerInitializer.newInstance();
    }

    // 3
    @Inject
    @Named("ServiceRegistry")
    ServiceRegistry sr;


    // test exception: https://howtodoinjava.com/junit5/expected-exception-example/
    @Test
    void registerThrowsExceptionWhenDuplicateUserWithSameUsername() throws RegistrationFailedException{
        try (SeContainer container = initializer.initialize()) {
            // FIXME nullpointer exception: datasource in JdbcPersonRepository is null
            ServiceRegistry serviceRegistry =
                    container.select(ServiceRegistry.class).get();
            // FIXME nameException (can't find service registry)
//            InitialContext initialContext = new InitialContext(); // 2
//            ServiceRegistry serviceRegistry = (ServiceRegistry)
//                    initialContext.lookup("ServiceRegistry"); // 2
            // FIXME injection doesn't work just like that
//            ServiceRegistry serviceRegistry = sr; // 3
            // note: tried using @Named("ServiceRegistry") with ServiceRegistry.java
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
        }
//        catch (NamingException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
    }

    // TODO test facade exceptions are thrown
//    @Test
//    public void authenticateThrowAuthenticationFailedExceptionWhenUserIsNotFound(){
//        try {
//            ServiceRegistry serviceRegistry = (ServiceRegistry)
//                    new InitialContext().lookup("ServiceRegistry");
//            IdentityManagementFacade identityManagementFacade =
//                    serviceRegistry.getIdentityManagementFacade();
//            AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
//                    .username("toto")
//                    .clearTextPassword("pwd")
//                    .build();
//            Assertions.assertThrows(AuthenticationFailedException.class, () -> {
//                identityManagementFacade.authenticate(authenticateCommand);
//            });
//        } catch (NamingException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
//    }
//
//    @Test
//    public void authenticateThrowAuthenticationFailedExceptionWhenUsingWrongCredential(){
//        try {
//            ServiceRegistry serviceRegistry = (ServiceRegistry)
//                    new InitialContext().lookup("ServiceRegistry");
//            IdentityManagementFacade identityManagementFacade =
//                    serviceRegistry.getIdentityManagementFacade();
//            RegisterCommand registerCommand = RegisterCommand.builder()
//                    .uuid(new PersonId())
//                    .username("Toto")
//                    .firstName("Marcel")
//                    .lastName("Dupont")
//                    .email("marcel.dupont@example.com")
//                    .clearTextPassword("pwd")
//                    .build();
//            identityManagementFacade.register(registerCommand);
//            AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
//                    .username("Toto")
//                    .clearTextPassword("pwd")
//                    .build();
//
//            Assertions.assertThrows(AuthenticationFailedException.class, () -> {
//                identityManagementFacade.authenticate(authenticateCommand);
//            });
//        } catch (NamingException | RegistrationFailedException e) {
//            e.printStackTrace();
//            Assert.fail();;
//        }
//    }
}