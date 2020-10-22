package ch.heigvd.amt.stack.ui.web.question.answer;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.QuestionsDTO;
import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;
import ch.heigvd.amt.stack.application.question.answer.AnswersQuery;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitAnswerPageEndpoint", urlPatterns = "/submitAnswer")
public class SubmitAnswerPageEndpoint extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    private AnswerFacade answerFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        answerFacade = serviceRegistry.getAnswerFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* TODO : Pouvoir lier une réponse à la liste de ses questions
        QuestionsDTO questionsDTO = answerFacade.getAnswers(AnswersQuery.builder().build());
        request.setAttribute("questions", questionsDTO);
        */
        request.getRequestDispatcher("/WEB-INF/views/submitAnswer.jsp").forward(request, response);
    }
}
