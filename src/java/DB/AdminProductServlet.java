package DB;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminProductServlet")
public class AdminProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try (Connection conn = DBConnection.getConnection()) {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = null;
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock(rs.getInt("stock"));
                    product.setCategoryId(rs.getInt("category_id"));
                }
                rs.close();
                ps.close();

                request.setAttribute("product", product);
                request.setAttribute("action", "edit");
                request.getRequestDispatcher("/admin/adminProductForm.jsp").forward(request, response);

            } else if ("add".equals(action)) {
                request.setAttribute("action", "add");
                request.getRequestDispatcher("/admin/adminProductForm.jsp").forward(request, response);

            } else if ("list".equals(action)) {
                List<Product> products = new ArrayList<>();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM products ORDER BY created_at DESC");
                while (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    p.setPrice(rs.getDouble("price"));
                    p.setStock(rs.getInt("stock"));
                    products.add(p);
                }
                rs.close();
                stmt.close();

                request.setAttribute("products", products);
                request.getRequestDispatcher("/admin/adminProductList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error managing products.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stock");
        String idStr = request.getParameter("id");

        double price = 0;
        int stock = 0;
        int id = 0;
        try { price = Double.parseDouble(priceStr); } catch (NumberFormatException ignored) {}
        try { stock = Integer.parseInt(stockStr); } catch (NumberFormatException ignored) {}
        try { id = Integer.parseInt(idStr); } catch (NumberFormatException ignored) {}

        try (Connection conn = DBConnection.getConnection()) {
            if ("edit".equals(action)) {
                PreparedStatement ps = conn.prepareStatement(
                    "UPDATE products SET name=?, description=?, price=?, stock=? WHERE id=?"
                );
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setDouble(3, price);
                ps.setInt(4, stock);
                ps.setInt(5, id);
                ps.executeUpdate();
                ps.close();

            } else if ("add".equals(action)) {
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO products (name, description, price, stock, created_at) VALUES (?, ?, ?, ?, NOW())"
                );
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setDouble(3, price);
                ps.setInt(4, stock);
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/AdminProductServlet?action=list");
    }
}
