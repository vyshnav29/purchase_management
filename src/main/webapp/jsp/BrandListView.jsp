<%@page import="java.util.Iterator"%>
<%@page import="in.co.inventor.mgt.sys.bean.BrandBean"%>
<%@page import="in.co.inventor.mgt.sys.bean.CategoryBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.inventor.mgt.sys.controller.BrandListCtl"%>
<%@page import="in.co.inventor.mgt.sys.controller.BrandCtl"%>
<%@page import="in.co.inventor.mgt.sys.util.ServletUtility"%>
<%@page import="in.co.inventor.mgt.sys.util.DataUtility"%>

<%@ include file="Header.jsp"%>
<form id="login-form" class="form" action="<%=IMSView.BRAND_LIST_CTL%>"
	method="post">
	<div id="login">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">Brand List</li>
			</ol>
		</nav>

		<div class="container">
		<h3 class="text-info">Brand List</h3>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
						</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
		<br>
			<div id="login-row" class="row justify-content ">
				<div id="login-column" class="col-md-12">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control" name="name" placeholder="Enter Name..." value="<%=ServletUtility.getParameter("name", request)%>">
						</div>
						<div class="col">
							<input type="submit" class="btn btn-info btn-md" value="<%=BrandListCtl.OP_SEARCH%>">
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
				<th scope="col"><input type="checkbox" id="selectall">Select
				All</th>
				<th scope="col">Image</th>
				<th scope="col">Name</th>
				<th scope="col">Description</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
		 <%
							int pageNo=ServletUtility.getPageNo(request);
							int pageSize=ServletUtility.getPageSize(request);
							int index=((pageNo-1)*pageSize)+1;
							int size=ServletUtility.getSize(request);
							List list=ServletUtility.getList(request);
							BrandBean bean=null;
							Iterator<BrandBean> iterator=list.iterator();
							while(iterator.hasNext()){
								 bean=iterator.next();
				%>
			<tr>
				<th scope="row"><%=index++%></th>
				<td><input type="checkbox" class="case" name="ids"
					value="<%=bean.getId()%>"></td>
				<td><img width="200px" height="100px" src="/Inventor-Managment/images/<%=bean.getImage()%>"/></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getDecription()%></td>
				<td><a class="btn btn-primary"
					href="<%=IMSView.BRAND_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	
	<table width="99%">
		<tr>
			<td><input type="submit" name="operation"
				class="btn btn-primary" value="<%=BrandListCtl.OP_PREVIOUS%>"
				<%=(pageNo == 1) ? "disabled" : ""%>></td>


			<td><input type="submit" name="operation"
				class="btn btn-primary" value="<%=BrandListCtl.OP_NEW%>"></td>
			<td><input type="submit" name="operation"
				value="<%=BrandListCtl.OP_DELETE%>" class="btn btn-primary"
				<%=(list.size() == 0) ? "disabled" : ""%>></td>
			
			<td align="right"><input type="submit" name="operation"
				class="btn btn-primary" value="<%=BrandListCtl.OP_NEXT%>"
				<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>></td>

		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
	
</form>
<div style="margin-top: 4px">
	<%@ include file="Footer.jsp"%>
</div>