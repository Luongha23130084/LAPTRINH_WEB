<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String ngay = request.getParameter("ngay");
	String soNguoi = request.getParameter("soNguoi");
	String idK = request.getParameter("idK");
	String buoi = request.getParameter("buoi");
	String tongTien = request.getParameter("tongTien");
	
	String ngoSen = request.getParameter("Ngo_Sen");
	String nem = request.getParameter("Nem");
	String muc = request.getParameter("Muc");
	String ga = request.getParameter("Ga");
	String lon = request.getParameter("Lon");
	
	int idD=0;
	String monAn=null, soluong=null;
	String trc13h=null, sau13h=null, idKTrc13h=null, idKSau13h=null, trc="1", sau="2";
	if(buoi.equals(trc)){
		trc13h="1";
		idKTrc13h=idK;
	}else{
		sau13h="1";
		idKSau13h=idK;
	}
//	KhachHangManager km = new KhachHangManager();
//	idD = km.nhapDatTruoc(idK, soNguoi, ngay, buoi, tongTien);	
//	km.nhapThongTinBan(idD, soNguoi, ngay, trc13h, idKTrc13h, sau13h, idKSau13h);
//	km.nhapDatMon(idD, "Ngó Sen", ngoSen);
//	km.nhapDatMon(idD, "Nem", nem);
//	km.nhapDatMon(idD, "Mực", muc);
//	km.nhapDatMon(idD, "Gà", ga);
//	km.nhapDatMon(idD, "Lợn", lon);
//	int id= 32767 - idD;
//	response.sendRedirect("success.jsp?id="+id);
%>
<link href="..\css\PageNhaHang.css" rel="stylesheet" type="text/css" />
<title>Xin chào</title>
</head>
<body>
	<p><%=ngoSen%></p>
	<p><%=nem%></p>
	<p><%=muc%></p>
	<p><%=ga%></p>
	<p><%=lon%></p>
</body>
</html>