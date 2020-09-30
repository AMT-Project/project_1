package ch.heigvd.amt.stack.application;

import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.infrastructure.persistence.memory.InMemoryPersonRepository;

public class ServiceRegistry {

    private static ServiceRegistry singleton;

    private static IPersonRepository personRepository;
    private static IdentityManagementFacade identityManagementFacade;

    public static ServiceRegistry getServiceRegistry() {
        if(singleton == null) {
            singleton = new ServiceRegistry();
        }
        return singleton;
    }

    public IdentityManagementFacade getIdentityManagementFacade() {
        return identityManagementFacade;
    }

    private ServiceRegistry() {
        singleton = this;
        personRepository = new InMemoryPersonRepository();
        identityManagementFacade = new IdentityManagementFacade(personRepository);
    }
}
