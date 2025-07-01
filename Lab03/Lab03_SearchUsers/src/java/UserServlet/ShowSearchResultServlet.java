package UserServlet;

import DBUtils.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowSearchResultServlet", urlPatterns = {"/ShowSearchResultServlet"})
public class ShowSearchResultServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchValue;
        int count;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Search result</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Search Result</h1>");
            searchValue = request.getParameter("txtSearchValue");
            out.println("Your search value is:" + searchValue+"</br>");
            List<User> result = (List<User>) request.getAttribute("SearchResult");
            StringBuilder strResult = new StringBuilder();
            if (result != null) {
                strResult.append("<table border='1'>");
                strResult.append("<thead>");
                strResult.append("<tr>");
                strResult.append("<th>No.</th>");
                strResult.append("<th>Username</th>");
                strResult.append("<th>Password</th>");
                strResult.append("<th>Lastname</th>");
                strResult.append("<th>Role</th>");
                strResult.append("<th></th>");
                strResult.append("</tr>");
                strResult.append("</thead>");
                strResult.append("<tbody>");
                count = 0;
                for (User user : result) {
                    strResult.append("<tr>");
                    strResult.append("<td>"+(++count)+".</td>");
                    strResult.append("<td>"+user.getUserName()+"</td>");
                    strResult.append("<td>"+user.getPassword()+"</td>");
                    strResult.append("<td>"+user.getLastName()+"</td>");
                    strResult.append("<td>"+user.isIsAdmin()+"</td>");
                    strResult.append("<td><a href='ProcessServlet?btnAction=Remove&&UserName=");
                    strResult.append(user.getUserName()+"'>");
                    strResult.append("Delete</td>");
                    strResult.append("</tr>");
                }
                strResult.append("</tbody>");
                strResult.append("</table>");
                out.println(strResult.toString());
            } else {
                out.println("<h3>No the users found.</h3>");
            }
            out.println("<a href='Search.html'>Back</a></br>");
            out.println("</body>");
            out.println("</html>");
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
