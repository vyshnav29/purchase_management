<%@page import="in.co.inventor.mgt.sys.controller.LoginCtl"%>
<%@page import="in.co.inventor.mgt.sys.controller.IMSView"%>
<%@page import="in.co.inventor.mgt.sys.bean.UserBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/Inventor-Managment/css/bootstrap.min.css" >
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<style type="text/css">

</style>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormate:'MM/dd/yy'
    });
  } );
  </script>
  
  <script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
</head>
<body>

 <%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;
%>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Inventor-Management</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="<%=IMSView.WELCOME_CTL%>">Home <span class="sr-only">(current)</span></a>
      </li>
      
      <%if(userLoggedIn){ %>
      
      <% if(userBean.getRoleId()==1){%>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Brand
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=IMSView.BRAND_CTL%>">Add Brand</a>
          <a class="dropdown-item" href="<%=IMSView.BRAND_LIST_CTL%>">Brand List</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Category
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=IMSView.CATEGORY_CTL%>">Add Category</a>
          <a class="dropdown-item" href="<%=IMSView.CATEGORY_LIST_CTL%>">Category List</a>
        </div>
      </li>
      
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Product
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
           <a class="dropdown-item" href="<%=IMSView.PRODUCT_CTL%>">Add Product</a>
          <a class="dropdown-item" href="<%=IMSView.PRODUCT_LIST_CTL%>">Product List</a>
        </div>
      </li>
      
     
      <li class="nav-item">
        <a class="nav-link" href="<%=IMSView.INVOICE_CTL%>">Add Order</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=IMSView.INVOICE_LIST_CTL%>">Order List</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=IMSView.INVOICE_DETAIL_LIST_CTL%>">Order Detail List</a>
      </li>
      
      <%}else if(userBean.getRoleId()==2){ %>
      
       <li class="nav-item">
        <a class="nav-link" href=">">Task List</a>
      </li>
      
      <%}%>
      
       <li class="nav-item">
        <a class="nav-link" href="<%=IMSView.MY_PROFILE_CTL%>">My Profile</a>
      </li>
      
       <li class="nav-item">
        <a class="nav-link" href="<%=IMSView.CHANGE_PASSWORD_CTL%>">Change Password</a>
      </li>
      
      <%}else{ %>
     
     	<li class="nav-item">
        <a class="nav-link" href="<%=IMSView.LOGIN_CTL%>">Login</a>
      </li>
      
      <%} %>
    </ul>
    
  
  </div>
  
  	<%if(userLoggedIn){ %>
   	<ul class="nav justify-content-end">
  	
 
  		<li class="nav-item">
        <a class="nav-link" href="<%=IMSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
      </li>
  	
	</ul>
	<%}%>
</nav>

