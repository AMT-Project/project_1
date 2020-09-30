package ch.heigvd.amt.stack.presentation;

import ch.heigvd.amt.stack.business.QuestionGenerator;
import ch.heigvd.amt.stack.domain.question.Question;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuestionServlet", urlPatterns = "/questions")
public class QuestionServlet extends HttpServlet {
  private QuestionGenerator service; // we will see later how to replace this with dependency injection

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    service = new QuestionGenerator();
  }

  protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    List<Question> model = service.generateQuestions();
    request.setAttribute("questions", model);
    request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
  }
}
