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

        
        List<Product> productList = productDAO.getAllProducts();

        
        request.setAttribute("products", productList);

        
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}


