package DB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection con = DBConnection.getConnection()) {
            // Only fetch what you need
            PreparedStatement ps = con.prepareStatement(
                "SELECT id, name, password_hash FROM admins WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");

                // TODO: Replace this with proper password hash check (e.g., BCrypt)
                if (password.equals(storedHash)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("adminId", rs.getInt("id"));
                    session.setAttribute("adminName", rs.getString("name"));
                    session.setAttribute("adminEmail", email);

                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("adminLogin.jsp?error=invalid");
                }
            } else {
                response.sendRedirect("adminLogin.jsp?error=notfound");
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminLogin.jsp?error=server");
        }
    }
}
