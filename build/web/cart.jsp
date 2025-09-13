<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="DB.CartItem" %>

<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
    <h1>Your Shopping Cart</h1>

    <c:choose>
        <c:when test="${not empty sessionScope.cart}">
            <table border="1" cellpadding="8">
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
                <c:set var="grandTotal" value="0" scope="page" />
                <c:forEach var="item" items="${sessionScope.cart}">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>₹${item.product.price}</td>
                        <td>${item.quantity}</td>
                        <td>₹${item.totalPrice}</td>
                    </tr>
                    <c:set var="grandTotal" value="${grandTotal + item.totalPrice}" scope="page" />
                </c:forEach>
                <tr>
                    <td colspan="3"><b>Grand Total</b></td>
                    <td><b>₹${grandTotal}</b></td>
                </tr>
            </table>
            <br>
            <form action="checkout.jsp" method="post">
                <button type="submit">Proceed to Checkout</button>
            </form>
        </c:when>
        <c:otherwise>
            <p>Your cart is empty.</p>
        </c:otherwise>
    </c:choose>

    <p><a href="products">Continue Shopping</a></p>
</body>
</html>
