<%@ include file="header.jsp" %>
<%@ page import="java.sql.ResultSet" %>
<html>
<head><title>Order History</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css" />


</head>


<body>
<h2>Your Orders</h2>
<%
    ResultSet orders = (ResultSet) request.getAttribute("orders");
    if (orders == null || !orders.next()) {
%>
    <p>You have no past orders.</p>
<%
    } else {
%>
<table border="1" cellpadding="5" cellspacing="0">
<tr><th>Order ID</th><th>Status</th><th>Address</th><th>Date</th></tr>
<%
    do {
%>
<tr>
    <td><%= orders.getInt("id") %></td>
    <td><%= orders.getString("status") %></td>
    <td><%= orders.getString("address") %></td>
    <td><%= orders.getTimestamp("created_at") %></td>
</tr>
<%
    } while (orders.next());
%>
</table>
<%
    }
%>
<a href="ProductListServlet">Continue Shopping</a>
</body>
</html>
