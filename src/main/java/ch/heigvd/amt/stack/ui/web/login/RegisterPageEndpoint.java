package ch.heigvd.amt.stack.ui.web.login;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name = "RegisterPageEndpoint", urlPatterns = "/register")
public class RegisterPageEndpoint extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }
}
