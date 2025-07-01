package Controllers.User;

import Models.DAO.UserDAO;
import Models.DTO.User;
import Models.DTO.UserError;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private final String userController = "UserController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName, password, lastName;
        boolean isAdmin = true, isError = false;
        String message = null;
        String url = userController;
        try {
            UserDAO userDAO = new UserDAO();
            userName = request.getParameter("txtUserName");
            password = request.getParameter("txtPassword");
            lastName = request.getParameter("txtLastName");
            String admin = request.getParameter("chkIsAdmin");
            UserError userError = new UserError();
            if(userName.matches("U\\d{3}") == false) {
                userError.setUserNameError("The UserName must be fortmatted Uxxx, x is digits.");
                isError = true;
            }
            if(password.matches("(.){3,15}") == false) {
                userError.setPasswordError("The Password must be 3 to 15 characters.");
                isError = true;
            }
            if(lastName.matches("(.){5,50}") == false) {
                userError.setLastNameError("The LastName must be 5 to 50 characters.");
                isError = true;
            }
            if(isError == false) {
                if(admin == null) {
                    isAdmin = false;
                }
                User user = new User(userName, password, lastName, isAdmin);
                if(userDAO.updateUser(user) == true) {
                    message = "<b style='color: green'>The user has been updated successfully</b>";
                } else {
                    message = "<b style='color: red'>Something went wrong</b>";
                }
            } else {
                request.setAttribute("ErrorDetails", userError);
            }
            url = userController+"?action=Details&UserName="+userName;
        } catch (Exception e) {
            log(e.getMessage());
        } finally {
            request.setAttribute("message", message);
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
