<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="DB.Order" %>
<html>
<head>
    <title>Admin - Order Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css" />
</head>
<body>
<h2>Order Management</h2>
<table border="1" cellpadding="5" cellspacing="0">
<tr>
    <th>Order ID</th>
    <th>User ID</th>
    <th>Status</th>
    <th>Total</th>
    <th>Date</th>
    <th>Action</th>
</tr>
<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    if (orders != null) {
        for (Order o : orders) {
%>
<tr>
    <td><%= o.getId() %></td>
    <td><%= o.getUserId() %></td>
    <td><%= o.getStatus() %></td>
    <td><%= o.getTotal() %></td>
    <td><%= o.getCreatedAt() %></td>
    <td>
        <form action="AdminOrderServlet" method="get">
            <input type="hidden" name="action" value="updateStatus"/>
            <input type="hidden" name="orderId" value="<%= o.getId() %>"/>
            <select name="status">
                <option value="Placed" <%= "Placed".equals(o.getStatus()) ? "selected" : ""%>>Placed</option>
                <option value="Shipped" <%= "Shipped".equals(o.getStatus()) ? "selected" : ""%>>Shipped</option>
                <option value="Delivered" <%= "Delivered".equals(o.getStatus()) ? "selected" : ""%>>Delivered</option>
                <option value="Cancelled" <%= "Cancelled".equals(o.getStatus()) ? "selected" : ""%>>Cancelled</option>
            </select>
            <input type="submit" value="Update"/>
        </form>
    </td>
</tr>
<%
        }
    }
%>
</table>
<a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>
