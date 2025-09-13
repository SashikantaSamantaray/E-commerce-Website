<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Checkout</title>
</head>
<body>
    <h1>Checkout</h1>

    <c:choose>
        <c:when test="${not empty sessionScope.cart}">
            <table border="1" cellpadding="8">
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
                <c:set var="grandTotal" value="0" />
                <c:forEach var="item" items="${sessionScope.cart}">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>${item.quantity}</td>
                        <td>₹${item.product.price}</td>
                        <td>₹${item.totalPrice}</td>
                    </tr>
                    <c:set var="grandTotal" value="${grandTotal + item.totalPrice}" />
                </c:forEach>
                <tr>
                    <td colspan="3"><b>Grand Total</b></td>
                    <td><b>₹${grandTotal}</b></td>
                </tr>
            </table>

            <form action="OrderServlet" method="post">
                <button type="submit">Place Order</button>
            </form>
        </c:when>
        <c:otherwise>
            <p>Your cart is empty. <a href="products">Go Shopping</a></p>
        </c:otherwise>
    </c:choose>
</body>
</html>
