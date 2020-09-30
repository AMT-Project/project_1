package ch.heigvd.amt.stack.application.question;



import ch.heigvd.amt.stack.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.AuthenticationFailedException;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.model.Question;

public class QuestionManagementFacade {
    private IQuestionRepository questionRepository;

    public QuestionManagementFacade(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void registerQuestion(Question newQuestion) {
    }

}
