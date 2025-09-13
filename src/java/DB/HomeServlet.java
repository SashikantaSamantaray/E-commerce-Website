package DB;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO productDAO = new ProductDAO();

        // ✅ Get only featured products
        List<Product> featuredProducts = productDAO.getFeaturedProducts();

        // ✅ Set attribute for JSP
        request.setAttribute("products", featuredProducts);

        // ✅ Forward to homepage (index.jsp)
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
