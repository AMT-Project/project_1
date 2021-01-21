package ch.heigvd.amt.stack.ui.web.question;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.SubmitQuestionCommand;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SubmitQuestionCommandEndpoint", urlPatterns = "/submitQuestion.do")
public class SubmitQuestionCommandEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();

        CurrentUserDTO currentUserDTO = (CurrentUserDTO) request.getSession().getAttribute("currentUser");

        SubmitQuestionCommand command = SubmitQuestionCommand.builder()
            .title(request.getParameter("title"))
            .authorUUID(currentUserDTO.getUuid())
            .text(request.getParameter("description"))
            .createdOn(LocalDate.now())
            .build();

        questionFacade.registerQuestion(command);
        response.sendRedirect(request.getContextPath() + "/login");

        serviceRegistry.getGamificationFacade().postParticipationEvent(currentUserDTO.getUuid(), "question");
    }
}
