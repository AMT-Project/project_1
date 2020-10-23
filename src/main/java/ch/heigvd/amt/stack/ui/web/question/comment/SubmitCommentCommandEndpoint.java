package ch.heigvd.amt.stack.ui.web.question.comment;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.application.question.answer.AnswerCommand;
import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;
import ch.heigvd.amt.stack.application.question.comment.CommentCommand;
import ch.heigvd.amt.stack.application.question.comment.CommentFacade;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitCommentCommandEndpoint", urlPatterns = "/submitComment.do")
public class SubmitCommentCommandEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentFacade commentFacade = serviceRegistry.getCommentFacade();

        CurrentUserDTO currentUserDTO = (CurrentUserDTO) request.getSession().getAttribute("currentUser");

        CommentCommand command = CommentCommand.builder()
                .authorUUID(currentUserDTO.getUuid())
                .questionUUID(new QuestionId("5b078997-1882-4119-aa52-2cdb82232886")) // TODO Besoin ?
                .answerUUID(new AnswerId("a414304c-4428-496e-a0ec-20fbdcb0da80"))
                .content(request.getParameter("content"))
                .build();

        commentFacade.registerComment(command);
        response.sendRedirect(request.getContextPath() + "/questions");
    }
}
