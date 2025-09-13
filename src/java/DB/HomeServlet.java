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

        
        List<Product> featuredProducts = productDAO.getFeaturedProducts();

       
        request.setAttribute("products", featuredProducts);

       
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

