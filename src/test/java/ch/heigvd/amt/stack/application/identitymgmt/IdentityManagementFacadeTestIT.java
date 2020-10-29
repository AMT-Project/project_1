package ch.heigvd.amt.stack.application.identitymgmt;

import ch.heigvd.amt.stack.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.infrastructure.persistence.jdbc.JdbcPersonRepository;
import ch.heigvd.amt.stack.infrastructure.persistence.jdbc.helper.DataSourceProvider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class IdentityManagementFacadeTestIT {

    static IPersonRepository repository;

    @BeforeAll
    public static void setup(){
        repository = new JdbcPersonRepository(DataSourceProvider.getDataSource());
    }

    @Test
    void registerThrowsRegistrationFailedExceptionWhenDuplicateUser() {
        final String TEST_NAME = "IdentityManagementFacadeTestIT_register"
                + (repository.count() + 1);
        IdentityManagementFacade identityManagementFacade = new IdentityManagementFacade(repository);
        RegisterCommand registerCommand = RegisterCommand.builder()
                .username(TEST_NAME)
                .firstName(TEST_NAME)
                .lastName(TEST_NAME)
                .email(TEST_NAME+"@example.com")
                .clearTextPassword("pwd")
                .build();
        try {
            identityManagementFacade.register(registerCommand);
        } catch (RegistrationFailedException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            fail();
        }
        Assertions.assertThrows(RegistrationFailedException.class, () -> {
            identityManagementFacade.register(registerCommand);
        });
    }

    // TODO
//    @Test
//    void authenticate() {
//    }
//
//    @Test
//    void countUsers() {
//    }
}