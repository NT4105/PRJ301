package UserServlet;

import DBUtils.UserDAO;
import DBUtils.User;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcessUserServlet", urlPatterns = { "/ProcessUserServlet" })
public class ProcessUserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName, password, lastName;
        boolean isAdmin = true;
        String action;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        action = request.getParameter("action").toLowerCase();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>User Management</title>");
            out.println("</head>");
            out.println("<body>");
            userName = request.getParameter("txtUserName");
            UserDAO userDAO = new UserDAO();
            if (action.equals("view")) {
                out.println("<h1>View User List" + "</h1>");
                List<User> userList = userDAO.getUserList();
                for (User user : userList) {
                    out.println(user + "</br>");
                }
            } else if (action.equals("delete")) {
                out.println("<h1>Users Management Delete user" + "</h1>");
                if (userDAO.deleteUser(userName) == true) {
                    out.println("User has been deleted successfully" + "</br>");
                } else {
                    out.println("<h3>Something went wrong!</h3></br>");
                }
            } else {
                password = request.getParameter("txtPassword");
                lastName = request.getParameter("txtLastName");
                String admin = request.getParameter("checkisAdmin");
                if (admin == null) {
                    isAdmin = false;
                }
                User user = new User(userName, password, lastName, isAdmin);
                if (action.equals("add")) {
                    out.println("<h1>Users Management - Add new user" + "</h1>");
                    if (userDAO.addUser(user) == true) {
                        out.println("User has been added successfully</br>");
                    } else {
                        out.println("<h3>Something went wrong!</h3></br>");
                    }
                } else if (action.equals("update")) {
                    out.println("<h1>Users Management - Update user" + "</h1>");
                    if (userDAO.updateUser(user) == true) {
                        out.println("User has been updated successfully</br>");
                    } else {
                        out.println("<h3>Something went wrong!</h3></br>");
                    }
                }
            }
            out.println("a href='Login.html'>Back to the login</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
