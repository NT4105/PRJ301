package Controllers.User;

import Models.DAO.UserDAO;
import Models.DTO.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DeleteController", urlPatterns = {"/DeleteController"})
public class DeleteController extends HttpServlet {

    private final String userController = "UserController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = userController;
        String message = null;
        try {
            UserDAO userDAO = new UserDAO();
            HttpSession session = request.getSession();
            User userLoggedIn = (User) session.getAttribute("userLoggedIn");
            String userName = request.getParameter("txtUserName");
            String searchValue = request.getParameter("txtSearchValue");
            if(userName.equals(userLoggedIn.getUserName())) {
                message = "<b style='color: red'>This user logged in, can not delete</b>";
            } else {
                if(!userName.isEmpty()) {
                    if(userDAO.deleteUser(userName) == true) {
                        message = "<b style='color: green'>The user has been deleted successfully.</b>";
                    } else {
                        message = "<b style='color: red'>Something went wrong</b>";
                    }
                }
            }
            url = userController+"?action=Search&txtSearchValue="+searchValue;
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
