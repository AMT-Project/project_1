package ch.heigvd.amt.stack.ui.web;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticsEndpoint", urlPatterns = "/statistics")
public class StatisticsEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();
        QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();
        AnswerFacade answerFacade = serviceRegistry.getAnswerFacade();

        int usersCount = identityManagementFacade.countUsers();
        int questionsCount = questionFacade.countQuestions();
        int answersCount = answerFacade.countAnswers();

        request.setAttribute("usersCount", usersCount);
        request.setAttribute("questionsCount", questionsCount);
        request.setAttribute("answersCount", answersCount);
        request.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(request, response);
    }
}
