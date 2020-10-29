package ch.heigvd.amt.stack.ui.web.login;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.identitymgmt.RegistrationFailedException;
import ch.heigvd.amt.stack.application.identitymgmt.login.RegisterCommand;
import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterCommandEndpoint", urlPatterns = "/register.do")
public class RegisterCommandEndpoint extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();

        request.getSession().removeAttribute("errors");

        RegisterCommand registerCommand = RegisterCommand.builder()
            .username(request.getParameter("username"))
            .firstName(request.getParameter("firstName"))
            .lastName(request.getParameter("lastName"))
            .email(request.getParameter("email"))
            .clearTextPassword(request.getParameter("password"))
            .build();
        try {
            identityManagementFacade.register(registerCommand);
            request.getRequestDispatcher("/login.do").forward(request, response);
        } catch(RegistrationFailedException e) {
            request.getSession().setAttribute("errors", List.of(e.getMessage()));
            response.sendRedirect(request.getContextPath() + "/register");
        }
    }
}
