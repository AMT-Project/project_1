package ch.heigvd.amt.stack.ui.web.question;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.PersonsQuery;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.QuestionsDTO;
import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.PersonsDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionQueryEndpoint", urlPatterns = "/questions")
public class QuestionQueryEndpoint extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    private QuestionFacade questionFacade;
    private IdentityManagementFacade identityManagementFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
        identityManagementFacade = serviceRegistry.getIdentityManagementFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonsDTO personsDTO = identityManagementFacade.getPersons(PersonsQuery.builder().build());
        QuestionsDTO questionsDTO = questionFacade.getQuestions(QuestionsQuery.builder().build());


        request.setAttribute("persons", personsDTO);
        request.setAttribute("questions", questionsDTO);
        request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
    }
}
