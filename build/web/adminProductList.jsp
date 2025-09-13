<%@ include file="header.jsp" %>
<%@ page import="java.sql.ResultSet" %>
<html>
<head><title>Admin - Products</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css" />

</head>
<body>
<h2>Product Management</h2>
<a href="AdminProductServlet?action=add">Add New Product</a>
<table border="1" cellpadding="5" cellspacing="0">
<tr><th>ID</th><th>Name</th><th>Price</th><th>Actions</th></tr>
<%
    ResultSet products = (ResultSet) request.getAttribute("products");
    while (products != null && products.next()) {
%>
<tr>
    <td><%= products.getInt("id") %></td>
    <td><%= products.getString("name") %></td>
    <td><%= products.getDouble("price") %></td>
    <td>
        <a href="AdminProductServlet?action=edit&id=<%= products.getInt("id") %>">Edit</a> |
        <a href="AdminProductServlet?action=delete&id=<%= products.getInt("id") %>"
           onclick="return confirm('Are you sure?');">Delete</a>
    </td>
</tr>
<%
    }
%>
</table>
<a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>
