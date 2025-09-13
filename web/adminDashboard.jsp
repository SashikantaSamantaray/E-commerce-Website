<%@ include file="header.jsp" %>
<%
    String adminName = (String) session.getAttribute("admin");
    if (adminName == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    }
%>
<html>
<head><title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css" />


</head>
<body>
<h2>Welcome, <%= adminName %></h2>
<ul>
    <li><a href="AdminProductServlet">Manage Products</a></li>
    <li><a href="AdminOrderServlet">Manage Orders</a></li>
    <li><a href="AdminUserServlet">Manage Users</a></li>
    <li><a href="adminLogout.jsp">Logout</a></li>
</ul>
</body>
</html>
