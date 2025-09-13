<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="DB.User" %>
<html>
<head>
    <title>Admin - User Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css" />
</head>
<body>
    <h2>User Management</h2>

    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Action</th>
        </tr>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (User u : users) {
        %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getPhone() %></td>
            <td>
                <a href="AdminUserServlet?action=delete&userId=<%= u.getId() %>"
                   onclick="return confirm('Are you sure you want to delete this user?');">
                   Delete
                </a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="5">No users found.</td></tr>
        <%
            }
        %>
    </table>

    <br/>
    <a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>
