package ch.heigvd.amt.stack.presentation;

import ch.heigvd.amt.stack.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "/RegisterCommandServlet", urlPatterns= "/registerold.do")
public class RegisterCommandServlet extends HttpServlet {
    public static final Map<String,String> users = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User userModel = new User(username,password);

        PrintWriter out = response.getWriter();

        if(users.containsKey(userModel.getUsername())){
            out.println("existe deja !");
        }else {
            users.put(userModel.getUsername(), userModel.getPassword());
        }

        users.forEach((user, pass) -> { out.println("USER = " + user + " : " + pass); });

    }
}
