package ch.heigvd.amt.stack.ui.web.profile;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.QuestionsDTO;
import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;
import ch.heigvd.amt.stack.application.question.answer.AnswersDTO;
import ch.heigvd.amt.stack.application.question.answer.AnswersQuery;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileEndpoint", urlPatterns = "/profile")
public class ProfileEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CurrentUserDTO currentUserDTO = (CurrentUserDTO) request.getSession().getAttribute("currentUser");
        QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();
        AnswerFacade answerFacade = serviceRegistry.getAnswerFacade();

        QuestionsDTO userQuestions = questionFacade.getQuestions(QuestionsQuery.builder()
            .authorUUID(currentUserDTO.getUuid())
            .build());

        AnswersDTO userAnswers = answerFacade.getAnswers(AnswersQuery.builder()
            .authorUUID(currentUserDTO.getUuid())
            .build());

        request.setAttribute("user", currentUserDTO);
        request.setAttribute("userAnswersCount", userAnswers.getAnswers().size());
        request.setAttribute("userQuestionsCount", userQuestions.getQuestions().size());
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}