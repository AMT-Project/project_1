package ch.heigvd.amt.stack.ui.web.question;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.question.GetQuestionQuery;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.QuestionsDTO;
import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;
import ch.heigvd.amt.stack.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionEndpoint", urlPatterns = "/question")
public class QuestionEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();
        AnswerFacade answerFacade = serviceRegistry.getAnswerFacade();

        request.getSession().removeAttribute("errors");

        // Default values
        int currentPage = 1;
        int recordsPerPage = 3;

        if(request.getParameter("currentPage") != null)
            currentPage = Integer.parseInt(request.getParameter("currentPage"));


        QuestionId questionUUID = new QuestionId(request.getParameter("uuid"));

        QuestionsDTO.QuestionDTO questionDTO = questionFacade.getQuestion(currentPage, recordsPerPage, GetQuestionQuery.builder()
            .uuid(questionUUID)
            .build());
        request.setAttribute("question", questionDTO);


        // Count rows in DB and calculate the nb of pages to display
        int rows = answerFacade.countAnswers();
        int noOfPages = rows / (recordsPerPage + 1);

        if(noOfPages % recordsPerPage > 0) {
            noOfPages++;
        }

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        request.getRequestDispatcher("/WEB-INF/views/question.jsp").forward(request, response);
    }
}
