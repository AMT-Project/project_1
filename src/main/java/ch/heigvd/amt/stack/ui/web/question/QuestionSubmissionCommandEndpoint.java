package ch.heigvd.amt.stack.ui.web.question;



import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.domain.question.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionSubmissionCommandEndpoint", urlPatterns = "/submitQuestion.do")
public class QuestionSubmissionCommandEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("errors");

        Question newQuestion = Question.builder()
                .question(request.getParameter("title"))
                .author("TODO")
                .build();
       /* try {
            identityManagementFacade.register(registerCommand);
            request.getRequestDispatcher("/login.do").forward(request, response);
            return;
        } catch(RegistrationFailedException e) {
            // TODO: problèmes avec la gestion des exceptions
            //request.getSession().setAttribute("errors", List.of(e.getMessage()));
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }*/
    }
}
