package ch.heigvd.amt.stack.ui.web.profile;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.application.identitymgmt.profile.UpdateUserCommand;
import ch.heigvd.amt.stack.application.identitymgmt.profile.UpdateUserFailedException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateProfileCommandEndpoint", urlPatterns = "/updateProfile.do")
public class UpdateProfileCommandEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();

        if(!request.getParameter("newPassword").equals(request.getParameter("repeatPassword"))) {
            request.getSession().setAttribute("errors", List.of("New password repeat doesn't match new password"));
            response.sendRedirect(request.getContextPath() + "/profile");
        }

        CurrentUserDTO currentUserDTO = (CurrentUserDTO) request.getSession().getAttribute("currentUser");
        UpdateUserCommand updateUserCommand = UpdateUserCommand.builder()
            .uuid(currentUserDTO.getUuid())
            .username(request.getParameter("username"))
            .newFirstname(request.getParameter("firstName"))
            .newLastname(request.getParameter("lastName"))
            .newEmail(request.getParameter("email"))
            .oldPasswordClear(request.getParameter("oldPassword"))
            .newPasswordClear(request.getParameter("newPassword"))
            .build();

        try {
            CurrentUserDTO updatedUser = identityManagementFacade.update(updateUserCommand);
            request.getSession().setAttribute("currentUser", updatedUser);
        } catch(UpdateUserFailedException e) {
            request.getSession().setAttribute("errors", List.of(e.getMessage()));

        }
        response.sendRedirect(request.getContextPath() + "/profile");
    }
}
