/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServletPkg;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
@WebServlet(name = "BookServlet", urlPatterns = { "/BookServlet" })
public class BookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("txtId");
        String title = request.getParameter("txtTitle");
        String author = request.getParameter("txtPublisher");
        double price = Double.parseDouble(request.getParameter("txtPrice"));
        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        double subTotal = price * quantity;
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>AddBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Book Details</h1>");
            StringBuilder strBookDetail = new StringBuilder();
            strBookDetail.append("<table border='1'>");
            strBookDetail.append("<thead>");
            strBookDetail.append("<tr>");
            strBookDetail.append("<th>BookId</th>");
            strBookDetail.append("<th>Title</th>");
            strBookDetail.append("<th>Publisher</th>");
            strBookDetail.append("<th>Price</th>");
            strBookDetail.append("<th>Quantity</th>");
            strBookDetail.append("<th>SubTotal</th>");
            strBookDetail.append("</tr>");
            strBookDetail.append("</thead>");
            strBookDetail.append("<tbody>");
            strBookDetail.append("<tr>");
            strBookDetail.append("<td>" + id + "</td>");
            strBookDetail.append("<td>" + title + "</td>");
            strBookDetail.append("<td>" + author + "</td>");
            strBookDetail.append("<td>" + price + "</td>");
            strBookDetail.append("<td>" + quantity + "</td>");
            strBookDetail.append("<td>" + subTotal + "</td>");
            strBookDetail.append("</tr>");
            strBookDetail.append("</tbody>");
            strBookDetail.append("</table>");
            out.println(strBookDetail.toString());
            out.println("<a href='CreateBook.html'>Back</a><br>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
