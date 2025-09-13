<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>${product.name} - Product Details</title>
    <style>
        .product-container {
            max-width: 900px;
            margin: 30px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0px 2px 6px rgba(0,0,0,0.1);
            display: flex;
            gap: 30px;
            background: #fff;
        }
        .product-container img {
            width: 300px;
            height: 300px;
            object-fit: cover;
            border-radius: 8px;
            border: 1px solid #ddd;
        }
        .product-details {
            flex: 1;
        }
        .product-details h2 {
            margin: 0 0 10px;
        }
        .product-details p {
            margin: 8px 0;
            font-size: 16px;
            color: #444;
        }
        .price {
            font-size: 20px;
            font-weight: bold;
            margin: 15px 0;
            color: #d9534f;
        }
        .btn {
            display: inline-block;
            padding: 10px 16px;
            background: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 15px;
        }
        .btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="product-container">
        
        <div>
            <img src="${pageContext.request.contextPath}/images/${fn:replace(product.imageUrl, 'images/', '')}" 
                 alt="${product.name}">
            
        </div>
        
        
        <div class="product-details">
            <h2>${product.name}</h2>
            <p><b>Category:</b> ${product.categoryName}</p>
            <p><b>Description:</b> ${product.description}</p>
            <p class="price">₹${product.price}</p>

            
            <form action="CartServlet" method="post">
                <input type="hidden" name="productId" value="${product.id}" />
                <button type="submit" class="btn">Add to Cart</button>
            </form>

            <p style="margin-top:20px;"><a href="products" class="btn" style="background:#6c757d;">Back to Products</a></p>
        </div>
    </div>
</body>
</html>
