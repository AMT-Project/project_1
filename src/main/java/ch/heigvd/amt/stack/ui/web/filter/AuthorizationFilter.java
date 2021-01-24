package ch.heigvd.amt.stack.ui.web.filter;

import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getRequestURI().endsWith("/stack/")){
            response.sendRedirect(request.getContextPath() + "/questions");
            return;
        }

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

            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        filterChain.doFilter(request, response);
    }

    boolean isPublicRessource(String URI) {
        return URI.startsWith("/stack/assets")
                || URI.startsWith("/stack/login")
                || URI.startsWith("/stack/logout")
                || URI.startsWith("/stack/register")
                || URI.startsWith("/stack/questions")
                || URI.startsWith("/stack/question")
                || URI.startsWith("/stack/statistics")
                || URI.endsWith("/stack/");
    }
}
