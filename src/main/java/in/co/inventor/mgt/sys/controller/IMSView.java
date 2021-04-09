package in.co.inventor.mgt.sys.controller;

public interface IMSView {
	
	public String APP_CONTEXT = "/Inventor-Managment";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	public String BRAND_VIEW = PAGE_FOLDER + "/BrandView.jsp"; 
	public String BRAND_LIST_VIEW = PAGE_FOLDER + "/BrandListView.jsp";
	
	public String INVOICE_VIEW = PAGE_FOLDER + "/InvoiceView.jsp"; 
	public String INVOICE_LIST_VIEW = PAGE_FOLDER + "/InvoiceListView.jsp";
	
	public String INVOICE_DETAIL_LIST_VIEW = PAGE_FOLDER + "/InvoiceDetailListView.jsp";
	
	public String PRODUCT_VIEW = PAGE_FOLDER + "/ProductView.jsp"; 
	public String PRODUCT_LIST_VIEW = PAGE_FOLDER + "/ProductListView.jsp";
	
	
	public String CATEGORY_VIEW = PAGE_FOLDER + "/CategoryView.jsp"; 
	public String CATEGORY_LIST_VIEW = PAGE_FOLDER + "/CategoryListView.jsp";

	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	
	
	public String BRAND_CTL = APP_CONTEXT + "/ctl/BrandCtl";
	public String BRAND_LIST_CTL = APP_CONTEXT + "/ctl/BrandListCtl";
	
	
	public String INVOICE_CTL = APP_CONTEXT + "/ctl/InvoiceCtl";
	public String INVOICE_LIST_CTL = APP_CONTEXT + "/ctl/InvoiceListCtl";
	
	public String INVOICE_DETAIL_LIST_CTL = APP_CONTEXT + "/ctl/InvoiceDetailListCtl";
	
	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/ProductCtl";
	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/ProductListCtl";
	
	public String CATEGORY_CTL = APP_CONTEXT + "/ctl/CategoryCtl";
	public String CATEGORY_LIST_CTL = APP_CONTEXT + "/ctl/CategoryListCtl";
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/myProfile";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/forgetPassword";



}
