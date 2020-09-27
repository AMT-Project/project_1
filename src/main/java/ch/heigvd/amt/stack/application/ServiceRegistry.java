package ch.heigvd.amt.stack.application;

import ch.heigvd.amt.stack.application.identifymgmt.IdentityManagementFacade;

public class ServiceRegistry {
    private static ServiceRegistry singleton; // TODO : will change in the future

    IdentityManagementFacade identityManagementFacade;

    public static ServiceRegistry getServiceRegistry() {
        if(singleton == null) {
            singleton = new ServiceRegistry();
        }
        return singleton;
    }

    public IdentityManagementFacade getIdentityManagementFacade() {
        return identityManagementFacade;
    }
}


