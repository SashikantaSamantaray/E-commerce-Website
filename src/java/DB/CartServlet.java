package DB;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Retrieve existing cart or create new one
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Get product ID & quantity from request
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = 1; // default 1
        if (request.getParameter("quantity") != null) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }

        // Fetch product from DB
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);

        // Check if product already in cart
        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(product, quantity));
        }

        // Save cart in session
        session.setAttribute("cart", cart);

        // Redirect to cart page
        response.sendRedirect("cart.jsp");
    }
}
