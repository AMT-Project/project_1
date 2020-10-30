package ch.heigvd.amt.stack.ui.web.question;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.QuestionsDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionQueryEndpoint", urlPatterns = "/questions")
public class QuestionsListEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();

        // Default values
        int currentPage = 1;
        int recordsPerPage = 3;

        if(request.getParameter("currentPage") != null)
            currentPage = Integer.parseInt(request.getParameter("currentPage"));

        QuestionsDTO questionsDTO = questionFacade.getQuestionsPagination(currentPage, recordsPerPage);
        request.setAttribute("questions", questionsDTO);

        // Count rows in DB and calculate the nb of pages to display
        int rows = questionFacade.countQuestions();
        int noOfPages = rows / recordsPerPage;

        if(noOfPages % recordsPerPage > 0) {
            noOfPages++;
        }

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
    }
}
