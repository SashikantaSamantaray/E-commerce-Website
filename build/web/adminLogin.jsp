<%@ include file="header.jsp" %>
<html>
<head><title>Admin Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css" />

</head>
<body>
<h2>Admin Login</h2>
<form action="AdminLoginServlet" method="post">
    Email: <input type="email" name="email" required /><br/>
    Password: <input type="password" name="password" required /><br/>
    <input type="submit" value="Login"/>
</form>
<%
    String error = request.getParameter("error");
    if ("true".equals(error)) {
%>
    <p style="color:red;">Invalid credentials</p>
<%
    }
%>
</body>
</html>
