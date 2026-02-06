<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="..\css\PageNhaHang.css" rel="stylesheet" type="text/css" />

<%
String ngay = request.getParameter("ngay");
String buoi = request.getParameter("buoi");
String idK = request.getParameter("idK");
String soNguoi = request.getParameter("soNguoi");
String idD = request.getParameter("idD");
%>
<script type="text/javascript" src="..\js\new1.js"></script>
<title>Insert title here</title>
</head>
</head>
<body>
	<div id="menu">
		<ul>
			<li><a href="PageNhaHang.jsp">Trang chủ</a></li>
			<li><a href="../food.jsp">Thực đơn</a></li>
			<li><a href="dattruoc.jsp">Đặt bàn</a></li>
			<li><a href="../promotion.jsp">Tin tức</a></li>
			<li><a href="#">Đăng nhập</a></li>
		</ul>
	</div>
	<form id="datMon" action="updateMon.jsp">
	<div class="container" style="margin-top: 50px">
		<input type="hidden" name="idD" value="<%=idD %>"/>
		<input type="hidden" name="idK" value="<%=idK%>"/>
		<input type="hidden" name="buoi" value="<%=buoi%>"/>
		<div class="column">
			<p>Ngày bạn tới</p>
			<input name="thoiGian" type="date" style="width: 200px" value="<%=ngay%>"></input>			
		</div>
		<div class="column">
			<p>Số người cùng đi</p>
			<input name="soNguoi" type="number" style="width: 200px"
				placeholder="Bạn đi cùng bao nhiêu người" value="<%=soNguoi%>"></input>
		</div>
		<div>
			<input type="radio" name="buoi" value="1">Trưa</input>
			<input type="radio" name="buoi" value="2">Tối</input>
		</div>
		<div>
			<input id="trua" type="radio" name="buoi" value="1">Trưa</input>
			<input id="toi" type="radio" name="buoi" value="2">Tối</input>
			<script type="text/javascript">
				getBuoi('<%=buoi%>');
			</script>
		</div>	
		<p id="kqDatBan"> </p>
	</div>
	<div class="container">		
		<div class="column" style="height: 400px; width: 400px">
			<img src="..\images\105_1456110385_84156ca7b316fb2b.jpg" >
			<br/>
			<input type="button" onclick="congMon(0)" value="Đặt"/>
			<input type="button" onclick="huyMon(0)" value="Giảm món"/>
			<p id="ngoSen" class="tenMonAn">Ngó Sen</p>
			<p style="display: inline">x100 000</p>
			<input type="hidden" id="ngosen_input" name="Ngo_Sen" value="0"/>
			<br/>
			<img src="..\images\247_1456110263_56056ca7ab71e3a6.jpg">
			<input type="button" onclick="congMon(1)" value="Đặt"/>
			<input type="button" onclick="huyMon(1)" value="Giảm món"/>
			<input type="hidden" id="nem_input" name="Nem" value="0"/>
			<p id="nem" class="tenMonAn">Nem</p>
			<p style="display: inline">x121 000</p>
			<br/>
			<img src="..\images\423_1456110519_36456ca7bb7c3c06.jpg">
			<input type="button" onclick="congMon(2)" value="Đặt"/>
			<input type="button" onclick="huyMon(2)" value="Giảm món"/>
			<input type="hidden" id="muc_input" name="Muc" value="0"/>
			<p id="muc" class="tenMonAn">Mực</p>
			<p style="display: inline">x199 000</p>
			<br/>
			<img src="..\images\518_1457491128_59256df8cb8ba3e0.jpg">
			<input type="button" onclick="congMon(3)" value="Đặt"/>
			<input type="button" onclick="huyMon(3)" value="Giảm món"/>
			<input type="hidden" id="ga_input" name="Ga" value="0"/>
			<p id="ga" class="tenMonAn">Gà</p>
			<p style="display: inline">x58 000</p>
			<br/>
			<img src="..\images\705_1456110231_78156ca7a9727f3f.jpg">
			<input type="button" onclick="congMon(4)" value="Đặt"/>
			<input type="button" onclick="huyMon(4)" value="Giảm món"/>
			<input type="hidden" id="lon_input" name="Lon" value="0"/>
			<p id="lon" class="tenMonAn">Lợn</p>
			<p style="display: inline">x63 000</p>
		</div>		
		<div class="column" style="height: 400px; width: 400px">
			<div>				
				<p id="giaTien">Tổng số tiền thanh toán</p>
				<input id="sum" name="tongTien" type="hidden" value="0"/>
				<br/>
				<input type="submit" value="Đặt"/>			
			</div>			
		</div>
	</div>
	</form>
</body>
</html>