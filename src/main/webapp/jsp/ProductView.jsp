<%@page import="in.co.inventor.mgt.sys.controller.ProductCtl"%>
<%@page import="in.co.inventor.mgt.sys.controller.CategoryCtl"%>
<%@page import="in.co.inventor.mgt.sys.util.HTMLUtility"%>
<%@page import="javax.swing.text.html.HTML"%>
<%@page import="java.util.List"%>
<%@page import="in.co.inventor.mgt.sys.controller.BrandCtl"%>
<%@page import="in.co.inventor.mgt.sys.util.ServletUtility"%>
<%@page import="in.co.inventor.mgt.sys.util.DataUtility"%>

<%@ include file="Header.jsp"%>
<div id="login">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Product</li>
		</ol>
	</nav>

	<div class="container">
		<div id="login-row" class="row justify-content align-items-center">
			<div id="login-column" class="col-md-6">
				<div id="login-box" class="col-md-12" style="margin-top: 30px;">
					<form id="login-form" class="form" action="<%=IMSView.PRODUCT_CTL%>"
						enctype="multipart/form-data" method="post">

						<jsp:useBean id="bean"
							class="in.co.inventor.mgt.sys.bean.ProductBean" scope="request"/>

						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="hidden" name="createdBy"
							value="<%=bean.getCreatedBy()%>"> <input type="hidden"
							name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
							type="hidden" name="createdDatetime"
							value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

						<h3 class="text-info">Add Product</h3>
						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
						</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
						<% List list=(List)request.getAttribute("brandList"); 
						List catlist=(List)request.getAttribute("catList");
						%>
						<div class="form-group">
							<label for="username" class="text-info">Brand Name:</label><br>
							<%=HTMLUtility.getList("brandId",String.valueOf(bean.getBrandId()),list) %>
							<font color="red"><%=ServletUtility.getErrorMessage("brandId", request)%></font>
						</div>
						
						<div class="form-group">
							<label for="username" class="text-info">Category Name:</label><br>
							<%=HTMLUtility.getList("categoryId",String.valueOf(bean.getCategoryId()),catlist) %>
							<font color="red"><%=ServletUtility.getErrorMessage("categoryId", request)%></font>
						</div>
						
						<div class="form-group">
							<label for="username" class="text-info">Date:</label><br>
							<input type="text" name="date" id="datepicker" class="form-control" placeholder="Enter Date..."
							 placeholder="Enter Date Here..."	value="<%=DataUtility.getDateString(bean.getDate())%>">
							<font color="red"><%=ServletUtility.getErrorMessage("date", request)%></font>
						</div>
						
						<div class="form-group">
							<label for="username" class="text-info">Name:</label><br>
							<input type="text" name="name" class="form-control" placeholder="Enter Name Here..."
								value="<%=DataUtility.getStringData(bean.getName())%>">
							<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
						</div>
						
						<div class="form-group">
							<label for="username" class="text-info">Price:</label><br>
							<input type="text" name="price" class="form-control" placeholder="Enter Price Here..."
								value="<%=(bean.getPrice()>0.0)?bean.getPrice():""%>">
							<font color="red"><%=ServletUtility.getErrorMessage("price", request)%></font>
						</div>
						<div class="form-group">
							<label for="username" class="text-info">Quantity:</label><br>
							<input type="text" name="quantity" class="form-control" placeholder="Enter Qunatity Here..."
								value="<%=(bean.getQuantity()>0)?bean.getQuantity():""%>">
							<font color="red"><%=ServletUtility.getErrorMessage("quantity", request)%></font>
						</div>
						<div class="form-group">
							<label for="password" class="text-info">Image:</label><br>
							<input type="file" name="photo" class="form-control"
								value="<%=DataUtility.getStringData(bean.getImage())%>">
							<font color="red"> <%=ServletUtility.getErrorMessage("photo", request)%></font>
						</div>
						
						<div class="form-group">
							<label for="username" class="text-info">Description:</label><br>
							<textarea  name="description" class="form-control" rows="4" cols="5" placeholder="Enter Description Here..."
								><%=DataUtility.getStringData(bean.getName())%></textarea>
							<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
						</div>
						
						<div class="form-group">
							<input type="submit" name="operation" class="btn btn-info btn-md"
								value="<%=ProductCtl.OP_SAVE%>"> or <input type="submit" name="operation" class="btn btn-info btn-md"
								value="<%=ProductCtl.OP_RESET%>">
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<div style="margin-top: 4px">
	<%@ include file="Footer.jsp"%>
</div>