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

    @Inject @Named("JdbcQuestionRepository")
    IQuestionRepository questionRepository;

    @Inject @Named("JdbcPersonRepository")
    IPersonRepository personRepository;

    public IdentityManagementFacade getIdentityManagementFacade() {
        return new IdentityManagementFacade(personRepository);
    }

    public QuestionFacade getQuestionFacade() {
        return new QuestionFacade(questionRepository, personRepository);
    }
}
