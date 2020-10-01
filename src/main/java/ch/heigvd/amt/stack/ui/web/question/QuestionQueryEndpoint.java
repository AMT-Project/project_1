package ch.heigvd.amt.stack.ui.web.question;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.QuestionsDTO;
import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.domain.question.Question;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuestionQueryEndpoint", urlPatterns = "/questions")
public class QuestionQueryEndpoint extends HttpServlet {
  private ServiceRegistry serviceRegistry;
  private QuestionFacade questionFacade;

  @Override
  public void init() throws ServletException{
    super.init();
    serviceRegistry = ServiceRegistry.getServiceRegistry();
    questionFacade = serviceRegistry.getQuestionFacade();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    QuestionsDTO questionsDTO = questionFacade.getQuestions(QuestionsQuery.builder().build());
    request.setAttribute("questions", questionsDTO);
    request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
  }
}
