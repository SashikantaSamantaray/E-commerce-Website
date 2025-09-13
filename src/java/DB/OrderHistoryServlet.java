package DB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            // TODO: Replace this with actual user_id lookup based on logged-in user
            int userId = 1;

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM orders WHERE user_id = ? ORDER BY created_at DESC");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            request.setAttribute("orders", rs);
            request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error loading order history.");
        }
    }
}
