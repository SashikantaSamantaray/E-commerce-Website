package DB;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotalPrice();
        }

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // ✅ Transaction start

            // Insert into orders
            String orderSql = "INSERT INTO orders (total, status) VALUES (?, 'Placed')";
            PreparedStatement psOrder = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            psOrder.setDouble(1, total);
            psOrder.executeUpdate();

            ResultSet rs = psOrder.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // Insert order items
            String itemSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement psItem = conn.prepareStatement(itemSql);
            for (CartItem item : cart) {
                psItem.setInt(1, orderId);
                psItem.setInt(2, item.getProduct().getId());
                psItem.setInt(3, item.getQuantity());
                psItem.setDouble(4, item.getProduct().getPrice());
                psItem.addBatch();
            }
            psItem.executeBatch();

            conn.commit(); // ✅ Transaction commit

            // Clear cart
            session.removeAttribute("cart");

            // Redirect to success page
            response.sendRedirect("order_success.jsp?id=" + orderId);

        } catch (Exception e) {
            try {
                response.getWriter().println("Error placing order: " + e.getMessage());
            } catch (IOException ex) {
            }
        }
    }
}
