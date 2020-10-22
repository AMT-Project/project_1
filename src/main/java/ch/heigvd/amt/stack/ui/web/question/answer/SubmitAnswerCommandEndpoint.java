package ch.heigvd.amt.stack.ui.web.question.answer;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.application.question.answer.AnswerCommand;
import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "SubmitAnswerCommandEndpoint", urlPatterns = "/submitAnswer.do")
public class SubmitAnswerCommandEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AnswerFacade answerFacade = serviceRegistry.getAnswerFacade();

        CurrentUserDTO currentUserDTO = (CurrentUserDTO) request.getSession().getAttribute("currentUser");

        AnswerCommand command = AnswerCommand.builder().authorUUID(currentUserDTO.getUuid()).
                questionUUID(new QuestionId(new UUID(10,10))). // TODO: A CHANGER
                content(request.getParameter("content"))
                .build();

        answerFacade.giveAnswer(command);
        response.sendRedirect(request.getContextPath() + "/questions");
    }
}
