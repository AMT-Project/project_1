package ch.heigvd.amt.stack.ui.web.login;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.AuthenticationFailedException;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginCommandEndpoint", urlPatterns = "/login.do")
public class LoginCommandEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();

        request.getSession().removeAttribute("errors");

        CurrentUserDTO currentUser = null;

        AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
            .username(request.getParameter("username"))
            .clearTextPassword(request.getParameter("password"))
            .build();

        try {
            currentUser = identityManagementFacade.authenticate(authenticateCommand);
            request.getSession().setAttribute("currentUser", currentUser);
            String targetUrl = (String) request.getSession().getAttribute("targetUrl");
            targetUrl = (targetUrl != null) ? targetUrl : (request.getContextPath() + "/questions");
            response.sendRedirect(targetUrl);
            return;
        } catch(AuthenticationFailedException e) {
//            request.getSession().setAttribute("errors", List.of(e.getMessage()));   //TODO
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
    }
}
