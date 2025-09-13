package DB;

import java.sql.*;
import java.util.*;

public class OrderDAO {

    public List<Map<String, Object>> getAllOrders() {
        List<Map<String, Object>> orders = new ArrayList<>();
        String sql = "SELECT o.id, o.total, o.status, o.created_at, " +
                     "oi.product_id, oi.quantity, oi.price, p.name AS product_name " +
                     "FROM orders o " +
                     "LEFT JOIN order_items oi ON o.id = oi.order_id " +
                     "LEFT JOIN products p ON oi.product_id = p.id " +
                     "ORDER BY o.created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> order = new HashMap<>();
                order.put("id", rs.getInt("id"));
                order.put("total", rs.getDouble("total"));
                order.put("status", rs.getString("status"));
                order.put("created_at", rs.getTimestamp("created_at"));
                order.put("product_name", rs.getString("product_name"));
                order.put("quantity", rs.getInt("quantity"));
                order.put("price", rs.getDouble("price"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
