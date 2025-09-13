package DB;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO productDAO = new ProductDAO();

        // ✅ Get all products
        List<Product> productList = productDAO.getAllProducts();

        // ✅ Set in request scope
        request.setAttribute("products", productList);

        // ✅ Forward to JSP
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}
