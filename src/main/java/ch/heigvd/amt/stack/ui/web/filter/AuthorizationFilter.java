package ch.heigvd.amt.stack.ui.web.filter;

import ch.heigvd.amt.stack.application.identifymgmt.authenticate.CurrentUserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(isPublicRessource(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        CurrentUserDTO currentUser = (CurrentUserDTO) request.getSession().getAttribute("currentUser");

        if(currentUser == null) {
            String targetUrl = request.getRequestURI();
            if(request.getQueryString() != null) {
                targetUrl += "?" + request.getQueryString();
            }
            request.getSession().setAttribute("targetUrl", targetUrl);
            request.getSession().removeAttribute("targetUrl");

            response.sendRedirect("/login");
            return;
        }
        filterChain.doFilter(request, response);
    }

    boolean isPublicRessource(String URI) {
        if(URI.startsWith("/assets")) {
            return true;
        }
        if(URI.startsWith("/login")) {
            return true;
        }
        if(URI.startsWith("/logout")) {
            return true;
        }
        if(URI.startsWith("/register")) {
            return true;
        }
        return false;
    }
}
