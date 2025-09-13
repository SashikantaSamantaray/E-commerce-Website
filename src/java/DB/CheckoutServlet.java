package DB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Map;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        String address = request.getParameter("address");
        String user = (String) session.getAttribute("user"); // Assume username stored on login

        if (user == null) { // Not logged in
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); 

            
            PreparedStatement psOrder = conn.prepareStatement(
                "INSERT INTO orders (user_id, address, status, created_at) VALUES (?, ?, ?, NOW())",
                Statement.RETURN_GENERATED_KEYS);
            
            psOrder.setInt(1, 1);
            psOrder.setString(2, address);
            psOrder.setString(3, "Placed");
            int affectedRows = psOrder.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Creating order failed.");

            ResultSet generatedKeys = psOrder.getGeneratedKeys();
            int orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }

            PreparedStatement psItem = conn.prepareStatement(
                "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)");

            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();

               
                PreparedStatement psPrice = conn.prepareStatement("SELECT price FROM products WHERE id = ?");
                psPrice.setInt(1, productId);
                ResultSet rsPrice = psPrice.executeQuery();
                if (!rsPrice.next()) throw new SQLException("Product not found: " + productId);
                double price = rsPrice.getDouble("price");
                rsPrice.close();
                psPrice.close();

                psItem.setInt(1, orderId);
                psItem.setInt(2, productId);
                psItem.setInt(3, quantity);
                psItem.setDouble(4, price);
                psItem.executeUpdate();

            }

            conn.commit(); 
            session.removeAttribute("cart"); 
            response.sendRedirect("orderSuccess.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (!session.isNew()) {
                    DBConnection.getConnection().rollback();
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            response.getWriter().write("Order processing failed. Please try again.");
        }
    }
}

