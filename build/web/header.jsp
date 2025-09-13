<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .navbar {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 10px 20px;
        background-color: #2874f0;
        color: white;
        font-family: Arial, sans-serif;
    }
    .navbar .logo a {
        font-size: 22px;
        font-weight: bold;
        color: white;
        text-decoration: none;
    }
    .navbar .search-bar {
        flex: 1;
        margin: 0 20px;
    }
    .navbar .search-bar input {
        width: 100%;
        padding: 6px;
        border-radius: 4px;
        border: none;
    }
    .navbar .nav-links a {
        margin-left: 15px;
        text-decoration: none;
        color: white;
        font-weight: bold;
    }
    .navbar .nav-links a:hover {
        text-decoration: underline;
    }
</style>

<div class="navbar">
    <div class="logo">
        <a href="index.jsp">GrabCheap</a>
    </div>

    <div class="search-bar">
        <form action="products" method="get">
            <input type="text" name="search" placeholder="Search products...">
        </form>
    </div>

    <div class="nav-links">
        <c:choose>
            <c:when test="${not empty sessionScope.userEmail}">
                <a href="logout.jsp">Logout</a>
                <a href="cart.jsp">Cart</a>
            </c:when>
            <c:otherwise>
                <a href="login.jsp">Login</a>
                <a href="register.jsp">Register</a>
                <a href="adminLogin.jsp">Admin</a>
                <a href="cart.jsp">Cart</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
