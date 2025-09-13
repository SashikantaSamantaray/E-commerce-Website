package DB;

import java.sql.*;
import java.util.*;

public class ProductDAO {

    
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT id, name, price, category_id, image_url FROM products";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setImageUrl(rs.getString("image_url")); // ✅ important

                products.add(p);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    
    public Product getProductById(int id) {
        Product product = null;

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT id, name, price, category_id, image_url FROM products WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setImageUrl(rs.getString("image_url"));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    List<Product> getFeaturedProducts() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

