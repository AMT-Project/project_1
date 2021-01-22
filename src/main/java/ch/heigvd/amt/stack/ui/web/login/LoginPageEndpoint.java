package ch.heigvd.amt.stack.ui.web.login;

import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginPageEndpoint", urlPatterns = "/login")
public class LoginPageEndpoint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (request.getSession().getAttribute("currentUser") != null){
           response.sendRedirect(request.getContextPath() + "/");
       }

        Object errors = request.getSession().getAttribute("errors");
        request.setAttribute("errors", errors);
        request.getSession().removeAttribute("errors");
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
