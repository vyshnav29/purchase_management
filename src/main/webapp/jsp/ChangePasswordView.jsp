
<%@page import="in.co.inventor.mgt.sys.controller.ChangePasswordCtl"%>
<%@page import="in.co.inventor.mgt.sys.util.ServletUtility"%>
<%@page import="in.co.inventor.mgt.sys.util.DataUtility"%>
<%@ include file="Header.jsp"%>
<div id="login">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Change Password</li>
		</ol>
	</nav>

	<div class="container">
		<div id="login-row" class="row justify-content align-items-center">
			<div id="login-column" class="col-md-6">
				<div id="login-box" class="col-md-12" style="margin-top: 30px;">
					<form id="login-form" class="form" action="<%=IMSView.CHANGE_PASSWORD_CTL%>"
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

						<h3 class="text-info">Change Password</h3>
						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
						</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
						
						<div class="form-group">
							<label for="password" class="text-info">Old Password:</label><br>
							<input type="password" placeholder="Enter Old Password..." name="oldPassword" class="form-control"
								value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>>
							<font color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
						</div>
						
						
						<div class="form-group">
							<label for="password" class="text-info">New Password:</label><br>
							<input type="password" name="newPassword" placeholder="Enter New Password..." class="form-control"
								value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%>>
							<font color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font>
						</div>
						
						<div class="form-group">
							<label for="password" class="text-info">Confirm Password:</label><br>
							<input type="password" name="confirmPassword" placeholder="Enter Confirm Password..." class="form-control"
								value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>>
							<font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
						</div>
						
						
						
						<div class="form-group">
							<input type="submit" name="operation" class="btn btn-info btn-md"
								value="<%=ChangePasswordCtl.OP_SAVE%>">
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