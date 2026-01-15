<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.User" %>

<%
    User admin = (User) session.getAttribute("admin");

%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/layout.css">
</head>
<body>

<h1>Xin chào Admin: <%= admin.getFullName() %></h1>

<ul>
    <li><a href="manage-users.jsp">Quản lý người dùng</a></li>
    <li><a href="manage-reservations.jsp">Quản lý đặt bàn</a></li>
    <li><a href="statistics.jsp">Thống kê</a></li>
    <li><a href="admin-logout.jsp">Đăng xuất</a></li>
</ul>

</body>
</html>
