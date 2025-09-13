<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Product Catalog</title>
    <style>
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            text-align: center;
            background: #fff;
            box-shadow: 0px 2px 5px rgba(0,0,0,0.1);
            transition: transform 0.2s;
        }
        .product-card:hover {
            transform: scale(1.05);
        }
        .product-card img {
            max-width: 100%;
            height: 160px;
            object-fit: cover;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .product-card h3 {
            font-size: 16px;
            margin: 8px 0;
        }
        .product-card p {
            margin: 5px 0;
            font-size: 14px;
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
    <h1>Product Catalog</h1>

    <c:choose>
        <c:when test="${not empty products}">
            <div class="product-grid">
                <c:forEach var="product" items="${products}">
                    <div class="product-card">
                        <!-- ✅ Clickable Image -->
                        <a href="ProductDetailServlet?id=${product.id}">
                            <img src="${pageContext.request.contextPath}/images/${fn:replace(product.imageUrl, 'images/', '')}" 
                                 alt="${product.name}">
                        </a>

                        <!-- ✅ Clickable Product Name -->
                        <h3>
                            <a href="ProductDetailServlet?id=${product.id}" style="text-decoration:none; color:#333;">
                                ${product.name}
                            </a>
                            <c:if test="${product.featured}"> ⭐</c:if>
                        </h3>

                        <p>${product.categoryName}</p>
                        <p><b>₹${product.price}</b></p>

                        <!-- ✅ Add to Cart -->
                        <form action="CartServlet" method="post">
                            <input type="hidden" name="productId" value="${product.id}" />
                            <button type="submit" class="btn">Add to Cart</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p>No products found.</p>
        </c:otherwise>
    </c:choose>

    <p style="margin-top:20px;"><a href="index.jsp" class="btn" style="background:#6c757d;">Back to Home</a></p>
</body>
</html>
