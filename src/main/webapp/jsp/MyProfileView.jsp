<%@page import="in.co.inventor.mgt.sys.controller.MyProfileCtl"%>
<%@page import="in.co.inventor.mgt.sys.util.ServletUtility"%>
<%@page import="in.co.inventor.mgt.sys.util.DataUtility"%>
<%@ include file="Header.jsp"%>
<div id="login">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">My Profile</li>
		</ol>
	</nav>

	<div class="container">
		<div id="login-row" class="row justify-content align-items-center">
			<div id="login-column" class="col-md-6">
				<div id="login-box" class="col-md-12" style="margin-top: 30px;">
					<form id="login-form" class="form" action="<%=IMSView.MY_PROFILE_CTL%>"
						method="post">

						<jsp:useBean id="bean"
							class="in.co.inventor.mgt.sys.bean.UserBean" scope="request"></jsp:useBean>

						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="hidden" name="createdBy"
							value="<%=bean.getCreatedBy()%>"> <input type="hidden"
							name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
							type="hidden" name="createdDatetime"
							value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

						<h3 class="text-info">My Profile</h3>
						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
						</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
						
						<div class="form-group">
							<label for="username" class="text-info">First Name:</label><br>
							<input type="text" name="firstName" class="form-control" placeholder="Enter First Name..."
								value="<%=DataUtility.getStringData(bean.getFirstName())%>">
							<font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font>
						</div>
						<div class="form-group">
							<label for="username" class="text-info">Last Name:</label><br>
							<input type="text" name="lastName" class="form-control" placeholder="Enter Last Name..."
								value="<%=DataUtility.getStringData(bean.getLastName())%>">
							<font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font>
						</div>
						
						<div class="form-group">
							<label for="username" class="text-info">Login Id:</label><br>
							<input type="text" name="login" class="form-control" placeholder="Enter Login Id..."
								value="<%=DataUtility.getStringData(bean.getLogin())%>">
							<font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
						</div>
						
						
						<div class="form-group">
							<label for="username" class="text-info">Mobile No:</label><br>
							<input type="text" name="mobile" class="form-control" placeholder="Enter Mobile No..."
								value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
							<font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
						</div>
						
						<div class="form-group">
							<input type="submit" name="operation" class="btn btn-info btn-md"
								value="<%=MyProfileCtl.OP_SAVE%>"> 
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<div style="margin-top: 162px">
	<%@ include file="Footer.jsp"%>
</div>