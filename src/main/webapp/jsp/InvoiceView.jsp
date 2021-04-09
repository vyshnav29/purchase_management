<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.inventor.mgt.sys.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.inventor.mgt.sys.controller.BrandCtl"%>
<%@page import="in.co.inventor.mgt.sys.util.ServletUtility"%>
<%@page import="in.co.inventor.mgt.sys.util.DataUtility"%>
<%@ include file="Header.jsp"%>



<div id="login">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">New Order</li>
		</ol>
	</nav>
	<div class="container">
		<h3 class="text-info">New Order</h3>
		<form id="login-form" class="form" action="<%=IMSView.INVOICE_CTL%>"
			 method="post">
			<jsp:useBean id="bean"
				class="in.co.inventor.mgt.sys.bean.InvoiceBean" scope="request" />
			<div class="row">
				<div class="col">
					<%
						List inlist = (List) request.getAttribute("prodList");
					%>
					<label for="username" class="text-info">Item Name:</label>
					<%=HTMLUtility.getList("itemId", String.valueOf(bean.getProductName()), inlist)%>
					<font color="red"><%=ServletUtility.getErrorMessage("itemId", request)%></font>
				</div>

				<div class="col">
					<label for="username" class="text-info">Quantity:</label> <input
						type="text" class="form-control" name="quantity"
						placeholder="Enter Quantity"
						value="<%=(bean.getQuantity() > 0) ? bean.getQuantity() : ""%>">
					<font color="red"><%=ServletUtility.getErrorMessage("quantity", request)%></font>
				</div>
				<div class="col">
					<label for="username" class="text-info">Price:</label> <input
						type="text" class="form-control" name="price" placeholder="Price"
						value="<%=(bean.getPrice() > 0.0) ? bean.getPrice() : ""%>"> <font
						color="red"><%=ServletUtility.getErrorMessage("price", request)%></font>
				</div>

				<div class="col">
					<label for="username" class="text-info">Total:</label> <input
						type="text" class="form-control" placeholder="Total">
				</div>
			</div>

			<div id="login-row" class="row justify-content align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12" style="margin-top: 30px;">




						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="hidden" name="createdBy"
							value="<%=bean.getCreatedBy()%>"> <input type="hidden"
							name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
							type="hidden" name="createdDatetime"
							value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
						</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
						<div class="form-group">
							<label for="username" class="text-info">Date:</label><br> <input
								type="text" name="date" class="form-control"
								placeholder="Enter Date..." id="datepicker"
								value="<%=DataUtility.getDateString(bean.getOrderDate())%>">
							<font color="red"><%=ServletUtility.getErrorMessage("date", request)%></font>
						</div>

						<div class="form-group">
							<label for="username" class="text-info">Name:</label><br> <input
								type="text" name="name" class="form-control"
								placeholder="Enter Name..."
								value="<%=DataUtility.getStringData(bean.getCustomerName())%>">
							<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
						</div>
						<div class="form-group">
							<label for="username" class="text-info">Sub Total:</label><br>
							<input type="text" name="subTotal" class="form-control"
								placeholder="Enter Sub Total..."
								value="<%=(bean.getSubTotal() > 0.0) ? bean.getSubTotal() : ""%>">
							<font color="red"><%=ServletUtility.getErrorMessage("subTotal", request)%></font>
						</div>

						<div class="form-group">
							<label for="username" class="text-info">Paid:</label><br> <input
								type="text" name="paid" class="form-control"
								placeholder="Paid..."
								value="<%=(bean.getPaid() > 0.0) ? bean.getPaid() : ""%>"> <font
								color="red"><%=ServletUtility.getErrorMessage("paid", request)%></font>
						</div>

						<div class="form-group">
							<label for="username" class="text-info">Due:</label><br> <input
								type="text" name="due" class="form-control"
								placeholder="Due..."
								value="<%=(bean.getDue() > 0.0) ? bean.getDue() : ""%>"> <font
								color="red"><%=ServletUtility.getErrorMessage("due", request)%></font>
						</div>

						<%
							LinkedHashMap map = new LinkedHashMap();
							map.put("Cash", "Cash");
							map.put("Card", "Card");
							map.put("NetBanking", "NetBanking");
						%>
						<div class="form-group">
							<label for="username" class="text-info">Payment Method:</label><br>
							<%=HTMLUtility.getList("paymentMethod", String.valueOf(bean.getPaidType()), map)%>
							<font color="red"><%=ServletUtility.getErrorMessage("paymentMethod", request)%></font>
						</div>




						<div class="form-group">
							<input type="submit" name="operation" class="btn btn-info btn-md"
								value="<%=BrandCtl.OP_SAVE%>"> or <input type="submit"
								name="operation" class="btn btn-info btn-md"
								value="<%=BrandCtl.OP_RESET%>">
						</div>


					</div>
				</div>
			</div>
		</form>
	</div>

</div>


<%@ include file="Footer.jsp"%>