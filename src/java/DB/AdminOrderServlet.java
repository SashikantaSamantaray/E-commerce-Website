package DB;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try (Connection conn = DBConnection.getConnection()) {

            if ("list".equals(action)) {
                // Fetch all orders
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id, user_id, status, total, created_at FROM orders ORDER BY created_at DESC"
                );
                ResultSet rs = ps.executeQuery();

                List<Order> orders = new ArrayList<>();
                while (rs.next()) {
                    Order o = new Order();
                    o.setId(rs.getInt("id"));
                    o.setUserId(rs.getInt("user_id"));
                    o.setStatus(rs.getString("status"));
                    o.setTotal(rs.getBigDecimal("total"));
                    o.setCreatedAt(rs.getTimestamp("created_at"));
                    orders.add(o);
                }

                rs.close();
                ps.close();

                // Send list to JSP
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("adminOrderList.jsp").forward(request, response);

            } else if ("updateStatus".equals(action)) {
                // Update order status
                String orderIdStr = request.getParameter("orderId");
                String status = request.getParameter("status");

                if (orderIdStr != null && status != null) {
                    int orderId = Integer.parseInt(orderIdStr);
                    PreparedStatement ps = conn.prepareStatement(
                            "UPDATE orders SET status = ? WHERE id = ?"
                    );
                    ps.setString(1, status);
                    ps.setInt(2, orderId);
                    ps.executeUpdate();
                    ps.close();
                }

                // Redirect to list
                response.sendRedirect(request.getContextPath() + "/AdminOrderServlet?action=list");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error managing orders: " + e.getMessage());
        }
    }
}
