<%@page import="in.co.inventor.mgt.sys.bean.InvoiceDetailBean"%>
<%@page import="in.co.inventor.mgt.sys.controller.InvoiceDetailListCtl"%>
<%@page import="in.co.inventor.mgt.sys.bean.InvoiceBean"%>
<%@page import="in.co.inventor.mgt.sys.controller.InvoiceListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.inventor.mgt.sys.bean.CategoryBean"%>
<%@page import="in.co.inventor.mgt.sys.bean.CategoryBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.inventor.mgt.sys.controller.CategoryListCtl"%>
<%@page import="in.co.inventor.mgt.sys.controller.CategoryCtl"%>
<%@page import="in.co.inventor.mgt.sys.util.ServletUtility"%>
<%@page import="in.co.inventor.mgt.sys.util.DataUtility"%>

<%@ include file="Header.jsp"%>
<form id="login-form" class="form" action="<%=IMSView.CATEGORY_LIST_CTL%>"
	method="post">
	<div id="login">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">Order Detail List</li>
			</ol>
		</nav>

		<div class="container">
		<h3 class="text-info">Order Detail List</h3>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
						</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
		<br>
			<div id="login-row" class="row justify-content ">
				<div id="login-column" class="col-md-12">
					<div class="row">
					
						<div class="col">
							<input type="text" class="form-control" placeholder="Enter Invoice No..." value="<%=ServletUtility.getParameter("inNO", request)%>">
						</div>
					
						<div class="col">
							<input type="text" class="form-control" placeholder="Enter Product Name..." value="<%=ServletUtility.getParameter("name", request)%>">
						</div>
						<div class="col">
							<input type="submit" class="btn btn-info btn-md" value="<%=InvoiceDetailListCtl.OP_SEARCH%>">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Invoice No</th>
				<th scope="col">Product Name</th>
				<th scope="col">Price</th>
				<th scope="col">Quantity</th>
			</tr>
		</thead>
		<tbody>
		 <%
							int pageNo=ServletUtility.getPageNo(request);
							int pageSize=ServletUtility.getPageSize(request);
							int index=((pageNo-1)*pageSize)+1;
							int size=ServletUtility.getSize(request);
							List list=ServletUtility.getList(request);
							InvoiceDetailBean bean=null;
							Iterator<InvoiceDetailBean> iterator=list.iterator();
							while(iterator.hasNext()){
								 bean=iterator.next();
				%>
			<tr>
				<th scope="row"><%=index++%></th>
			
				<td><%=bean.getInvoiceNo()%></td>
				<td><%=bean.getProductName()%></td>
				<td><%=bean.getPrice()%></td>
				<td><%=bean.getQuantity()%></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	
	<table width="99%">
		<tr>
			<td><input type="submit" name="operation"
				class="btn btn-primary" value="<%=InvoiceDetailListCtl.OP_PREVIOUS%>"
				<%=(pageNo == 1) ? "disabled" : ""%>></td>


			
			
			<td align="right"><input type="submit" name="operation"
				class="btn btn-primary" value="<%=InvoiceDetailListCtl.OP_NEXT%>"
				<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>></td>

		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
	
</form>
<div style="margin-top: 4px">
	<%@ include file="Footer.jsp"%>
</div>