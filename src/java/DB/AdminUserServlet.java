package DB;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminUserServlet")
public class AdminUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try (Connection con = DBConnection.getConnection()) {
            if ("list".equals(action)) {
                PreparedStatement ps = con.prepareStatement("SELECT id, name, email, phone FROM users ORDER BY id");
                ResultSet rs = ps.executeQuery();

                List<User> users = new ArrayList<>();
                while (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setEmail(rs.getString("email"));
                    u.setPhone(rs.getString("phone"));
                    users.add(u);
                }
                rs.close();
                ps.close();

                request.setAttribute("users", users);
                request.getRequestDispatcher("/admin/adminUserList.jsp").forward(request, response);

            } else if ("delete".equals(action)) {
                String userIdStr = request.getParameter("userId");
                if (userIdStr != null) {
                    int userId = Integer.parseInt(userIdStr);
                    PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
                    ps.setInt(1, userId);
                    ps.executeUpdate();
                    ps.close();
                }
                response.sendRedirect(request.getContextPath() + "/AdminUserServlet?action=list");
            }
        } catch(Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error managing users.");
        }
    }
}
