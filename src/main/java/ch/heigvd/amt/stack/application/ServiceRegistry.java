package ch.heigvd.amt.stack.application;

import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceRegistry {

    //private static ServiceRegistry singleton;

    @Inject @Named("InMemoryQuestionRepository")
    IQuestionRepository questionRepository;

    @Inject @Named("InMemoryPersonRepository")
    IPersonRepository personRepository;

    //private static QuestionFacade questionFacade;
    //private static IdentityManagementFacade identityManagementFacade;

    /*
    public static ServiceRegistry getServiceRegistry() {
        if(singleton == null) {
            singleton = new ServiceRegistry();
        }
        return singleton;
    }
     */

    public IdentityManagementFacade getIdentityManagementFacade() {
        return  new IdentityManagementFacade(personRepository);
    }

    public QuestionFacade getQuestionFacade() {
        return new QuestionFacade(questionRepository);
    }

    /*
    private ServiceRegistry() {
        singleton = this;
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository);
        personRepository = new InMemoryPersonRepository();
        identityManagementFacade = new IdentityManagementFacade(personRepository);
    }
    */
}
