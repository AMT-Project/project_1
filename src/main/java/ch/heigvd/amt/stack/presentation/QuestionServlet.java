package ch.heigvd.amt.stack.presentation;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.QuestionsDTO;
import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.business.QuestionGenerator;
import ch.heigvd.amt.stack.domain.question.Question;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuestionServlet", urlPatterns = "/questions")
public class QuestionServlet extends HttpServlet {
  /*private QuestionGenerator service; // we will see later how to replace this with dependency injection

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    service = new QuestionGenerator();
  }

  protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    List<Question> model = service.generateQuestions();
    request.setAttribute("questions", model);
    request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
  }*/
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
