<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DB.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>My E-Commerce Store</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css" />
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            margin-bottom: 20px;
        }
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 20px;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 10px;
            text-align: center;
            box-shadow: 0px 2px 5px rgba(0,0,0,0.1);
            transition: transform 0.2s;
            background: #fff;
        }
        .product-card:hover {
            transform: scale(1.05);
        }
        .product-card img {
            max-width: 100%;
            height: 150px;
            object-fit: cover;
            border-radius: 5px;
        }
        .product-card h3 {
            font-size: 16px;
            margin: 10px 0 5px;
        }
        .product-card p {
            margin: 5px 0;
            color: #555;
        }
        .btn {
            display: inline-block;
            margin-top: 10px;
            padding: 8px 12px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Welcome to My Store</h2>

    <div class="product-grid">
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null) {
                for (Product p : products) {
        %>
        <div class="product-card">
            <img src="<%= request.getContextPath() %>/images/<%= p.getImageUrl().replace("images/", "") %>"
                 alt="<%= p.getName() %>">
            <h3><%= p.getName() %></h3>
            <p><b>₹<%= p.getPrice() %></b></p>
            <p><%= p.getCategoryId() %></p>
            <a href="addToCartServlet?productId=<%= p.getId() %>" class="btn">Add to Cart</a>
        </div>
        <%
                }
            }
        %>
    </div>
</body>
</html>
