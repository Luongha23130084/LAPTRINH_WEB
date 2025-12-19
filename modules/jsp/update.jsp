
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
String maKhachHang = request.getParameter("idD");
int idD = 0;
if(maKhachHang!=null) {
	idD = Integer.parseInt(maKhachHang);
	idD = 32767-idD;
	
}
//KhachHangManager km = new KhachHangManager();
//ModelsKhachHang dondathang=km.xacNhanDonHang(idD);
//if(dondathang!=null){	
//	response.sendRedirect("datlai.jsp?idD="+idD
//			+"&ngay="+dondathang.getNgay()+"&buoi="+dondathang.getBuoi()
//			+"&idK="+dondathang.getIdK()+"&soNguoi="+dondathang.getSoNguoi());
//}else response.sendRedirect("PageNhaHang.jsp?confirm="+4);
%>
</head>
<body>

</body>
</html>