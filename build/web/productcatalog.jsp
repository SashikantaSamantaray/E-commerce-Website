<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="DB.Product" %>

<html>
<head>
    <title>Product Catalog</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container my-4">
    <h2 class="mb-4 text-center">All Products</h2>
    <div class="row g-4">
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (Product p : products) {
        %>
        <div class="col-md-4 col-lg-3">
            <div class="card h-100 shadow-sm">
                <img src="<%= request.getContextPath() + "/" + p.getImageUrl() %>" 
                     class="card-img-top" 
                     alt="<%= p.getName() %>" 
                     style="height:200px; object-fit:cover;">
                <div class="card-body text-center">
                    <h5 class="card-title"><%= p.getName() %></h5>
                    <p class="card-text text-muted">₹<%= p.getPrice() %></p>
                    <p class="small">Stock: <%= p.getStock() %></p>
                    <a href="addToCart?id=<%= p.getId() %>" class="btn btn-primary">Add to Cart</a>
                </div>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <div class="col-12 text-center">
            <p>No products available.</p>
        </div>
        <%
            }
        %>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
