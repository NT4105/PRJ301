package Controllers.User;

import Models.DAO.UserDAO;
import Models.DTO.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private final String displayMessagePage = "DisplayMessage.jsp";
    private final String loginPage = "Login.html";
    private final String userController = "UserController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName, password, lastName;
        boolean isAdmin = true;
        String message = null;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = loginPage;
        try {
            HttpSession session = request.getSession();
            User userLoggedIn = (User) session.getAttribute("userLoggedIn");
            userName = request.getParameter("txtUserName");
            password = request.getParameter("txtPassword");
            lastName = request.getParameter("txtLastName");
            String admin = request.getParameter("chkIsAdmin");
            if (admin == null) {
                isAdmin = false;
            }
            if (!userName.isEmpty()) {
                User user = new User(userName, password, lastName, isAdmin);
                UserDAO userDao = new UserDAO();
                if (userDao.updateUser(user) == true) {
                    if (userLoggedIn.isIsAdmin()) {
                        url = userController+"?action=Search";
                    }
                } else {
                    url = displayMessagePage;
                    message = "The user '" + userName + "' has been updated successfully";
                }
            }

        } catch (Exception ex) {
            log(ex.getMessage());
        } finally {
            request.setAttribute("action", "Update User");
            request.setAttribute("page", "Search.jsp");
            request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
