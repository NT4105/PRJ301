package Controllers.Cart;

import Models.Entities.CartItem;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "SaveCartController", urlPatterns = {"/SaveCartController"})
public class SaveCartController extends HttpServlet {
    private final String cartController = "CartController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = null;
        String message = null;
        HashMap<String, CartItem> itemsInCart = null;
        try {
            HttpSession shoppingCart = request.getSession();
            itemsInCart = (HashMap<String, CartItem>) shoppingCart.getAttribute("Cart");
            CartUtil cart = new CartUtil();
            if (itemsInCart != null) {
                String strItemsInCart = cart.convertCartToString (new ArrayList<CartItem>(itemsInCart.values()));
                cart.saveCarttoCookie(request, response, strItemsInCart);
                message = "Your cart has been save succesfully!!!";
            } else {
                message = "Your cart is empty!!!";
            }
            url = cartController + "?action=View Cart";
        } catch (Exception ex) {
            log ("SaveCartController has error: " + ex.getMessage());
        } finally {
            request.setAttribute("Message", "<h4>" + message + "</h4>");
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
