package ch.heigvd.amt.stack.ui.web.question;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionSubmissionPageEndpoint", urlPatterns = "/submitQuestion")
public class QuestionSubmissionPageEndpoint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object errors = request.getSession().getAttribute("errors");
        request.setAttribute("errors", errors);
        request.getSession().removeAttribute("errors");
        request.getRequestDispatcher("/WEB-INF/views/submitQuestion.jsp").forward(request, response);
    }
}