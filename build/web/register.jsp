<%@ include file="header.jsp" %>
<form action="RegisterServlet" method="post">
    Name: <input type="text" name="name"/><br/>
    Email: <input type="email" name="email"/><br/>
    Password: <input type="password" name="password"/><br/>
    Phone: <input type="text" name="phone"/><br/>
    <input type="submit" value="Register"/>
</form>
