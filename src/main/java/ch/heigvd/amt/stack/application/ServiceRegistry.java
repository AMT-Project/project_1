package ch.heigvd.amt.stack.application;

import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.infrastructure.persistence.memory.InMemoryPersonRepository;
import ch.heigvd.amt.stack.infrastructure.persistence.memory.InMemoryQuestionRepository;

public class ServiceRegistry {

    private static ServiceRegistry singleton;

    private static IQuestionRepository questionRepository;
    private static QuestionFacade questionFacade;

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
    public QuestionFacade getQuestionFacade(){ return questionFacade;}

    private ServiceRegistry() {
        singleton = this;
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository);
        personRepository = new InMemoryPersonRepository();
        identityManagementFacade = new IdentityManagementFacade(personRepository);
    }
}
