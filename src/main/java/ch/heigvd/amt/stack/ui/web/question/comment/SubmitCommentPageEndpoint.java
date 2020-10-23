package ch.heigvd.amt.stack.ui.web.question.comment;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.question.comment.CommentFacade;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitCommentPageEndpoint", urlPatterns = "/submitComment")
public class SubmitCommentPageEndpoint extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    private CommentFacade commentFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        commentFacade = serviceRegistry.getCommentFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("questionUuid") != null) {
            request.setAttribute("questionUuid", request.getParameter("questionUuid"));
        }
        else {
            request.setAttribute("questionUuid", null);
        }

        if(request.getParameter("answerUuid") != null){
            request.setAttribute("answerUuid", request.getParameter("answerUuid"));
        }
        else{
            request.setAttribute("answerUuid", null);
        }
        request.getRequestDispatcher("/WEB-INF/views/submitComment.jsp").forward(request, response);
    }
}