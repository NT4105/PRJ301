package Controllers.Authentication;

import Models.DTO.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;



public class AuthenticationFilter implements Filter {
    private static final String loginPage = "Login.jsp";
    private final String createController = "CreateController";
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("Create")) {
                request.getRequestDispatcher(createController).forward(request, response);
            }
        if (session != null) {
            User user = (User) session.getAttribute("userLoggedIn");
            if (user != null) {
                req.setAttribute("loggedByAdmin", user.isIsAdmin());
                chain.doFilter(request, response);
            }
        } else {
            resp.sendRedirect(loginPage);
        }
    }
    public void destroy() {        
    }
    public void init(FilterConfig filterConfig) {
        
    }        
}
