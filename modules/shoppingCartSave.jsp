
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Formatter"%>
<%@page import="java.util.Date" %>
<%@page import="java.util.List"%>
<%@page import="java.lang.Integer" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

%>
<%	
	String idfd;
	idfd = request.getParameter("id");
	
//	FoodManagement fdm = new FoodManagement();
//	Food fd = fdm.getById(idfd);
	
	String s =request.getParameter("number");
	int iNumber =Integer.parseInt(s);
//	int totalCost = iNumber*(fd.getCost());
	Date today = new Date();
	SimpleDateFormat parsed = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH);
	String now = parsed.format(today);
	System.out.println("-----Date:"+now);
//	System.out.println("-----totalCost:"+totalCost);
	
//	OrderCustomer oc = new OrderCustomer();
//	oc.setCusName(request.getParameter("cusName"));
//	oc.setEmail(request.getParameter("email"));
//	oc.setAddress(request.getParameter("address"));
//	oc.setPhone(request.getParameter("phone"));
//	oc.setDescription(request.getParameter("description"));
//	OrderCusManager ocm = new OrderCusManager();
//	int ocId = ocm.insert(oc);
//	System.out.println("-----------ocId"+ocId);
	
//	Orders os = new Orders();
//	os.setDeliveryDate("");
//	os.setCloseDate("");
//	os.setOdStatus("Mới đặt");
//	os.setOrderCusId(ocId);
//	OrdersManager osm = new OrdersManager();
//	int ordersId = osm.insert(os);
//	System.out.println("-----------ordersId"+ordersId);
	
	//insert table orderdetail
//	OrderDetail2 od = new OrderDetail2();
//	od.setFoodId(Integer.parseInt(idfd));
//	od.setNumber(iNumber);
//	od.setTotalCost(totalCost);
//	od.setOrderDate(now);
//	od.setOrdersId(ordersId);
//	OrderDetail2Manager odm = new OrderDetail2Manager();
//	odm.insert(od);
//	response.sendRedirect("food.jsp");
%>