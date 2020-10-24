package ch.heigvd.amt.stack.application;

import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;
import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.comment.CommentFacade;
import ch.heigvd.amt.stack.domain.question.answer.IAnswerRepository;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.comment.ICommentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceRegistry {

    @Inject
    @Named("JdbcPersonRepository")
    IPersonRepository personRepository;

    @Inject
    @Named("JdbcQuestionRepository")
    IQuestionRepository questionRepository;

    @Inject
    @Named("JdbcAnswerRepository")
    IAnswerRepository answerRepository;

    @Inject
    @Named("JdbcCommentRepository")
    ICommentRepository commentRepository;


    public IdentityManagementFacade getIdentityManagementFacade() {
        return new IdentityManagementFacade(personRepository);
    }

    public QuestionFacade getQuestionFacade() {
        return new QuestionFacade(questionRepository, personRepository, getCommentFacade(), getAnswerFacade());
    }

    public AnswerFacade getAnswerFacade() {
        return new AnswerFacade(answerRepository, questionRepository, personRepository, getCommentFacade());
    }

    public CommentFacade getCommentFacade() {
        return new CommentFacade(commentRepository, answerRepository, questionRepository, personRepository);
    }
}
