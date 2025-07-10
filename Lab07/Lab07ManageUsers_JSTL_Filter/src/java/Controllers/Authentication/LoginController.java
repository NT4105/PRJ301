package Controllers.Authentication;

import Models.DAO.Services.UserService;
import Models.DTO.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    private final String searchPage = "Search.jsp";
    private final String userController = "UserController";
    private final String loginPage = "Login.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = loginPage;
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        try {
            UserService userService = new UserService();
            User user = userService.checkAccount(userName, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userLoggedIn", user);
                if (user.isIsAdmin()) {
                    url = searchPage;
                }
                else {
                    url = userController+"?action=Details&&UserName"+userName;
                }
            }
            else {
                request.setAttribute("message", "The user name or password is invalid");
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }   
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
