<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Admin - Orders</title>
</head>
<body>
    <h1>All Orders</h1>

    <c:choose>
        <c:when test="${not empty orders}">
            <table border="1" cellpadding="8">
                <tr>
                    <th>Order ID</th>
                    <th>Product</th>
                    <th>Qty</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Status</th>
                    <th>Action</th>
                    <th>Placed At</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.product_name}</td>
                        <td>${order.quantity}</td>
                        <td>₹${order.price}</td>
                        <td>₹${order.total}</td>
                        <td>${order.status}</td>
                        <td>
                            <form action="orders" method="post">
                                <input type="hidden" name="orderId" value="${order.id}" />
                                <select name="status">
                                    <option>Pending</option>
                                    <option>Placed</option>
                                    <option>Shipped</option>
                                    <option>Delivered</option>
                                    <option>Cancelled</option>
                                </select>
                                <button type="submit">Update</button>
                            </form>
                        </td>
                        <td>${order.created_at}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No orders found.</p>
        </c:otherwise>
    </c:choose>

    <p><a href="../home">Back to Home</a></p>
</body>
</html>
