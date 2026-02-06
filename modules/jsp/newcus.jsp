<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>

<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String ten = request.getParameter("ten");
	String email = request.getParameter("email");
	String sdt = request.getParameter("sdt");
//	KhachHangManager km = new KhachHangManager();
//	ModelsKhachHang cus;
	int idK;
	String ngay = request.getParameter("ngay");
	String buoi = request.getParameter("buoi");
	String soNguoi = request.getParameter("soNguoi");
	int int_soNguoi=0;
	String maKhachHang=null;
	maKhachHang = request.getParameter("maKhachHang");	
	try {
		if(soNguoi!=null) int_soNguoi = Integer.parseInt(soNguoi);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		soNguoi = null;
	}
	if(maKhachHang !=null && maKhachHang.trim().length()>0) response.sendRedirect("update.jsp?idD="+maKhachHang);
	else if(ten==null || email ==null || sdt==null || ngay ==null || buoi ==null || soNguoi == null)
		response.sendRedirect("PageNhaHang.jsp?confirm="+2);
//	else if(km.xacNhanBan(ngay, buoi)*6 < int_soNguoi) response.sendRedirect("PageNhaHang.jsp?confirm="+3);
	else {
//		idK = km.nhapKhachHang(ten, email, sdt);
//		response.sendRedirect("dattruoc.jsp?ngay="+ngay+"&soNguoi="+soNguoi+"&idK="+idK+"&buoi="+buoi);
	}
%>

</body>
</html>