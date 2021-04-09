package in.co.inventor.mgt.sys.controller;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.inventor.mgt.sys.util.ServletUtility;


/**
 * 
 * 
 * Servlet implementation class WelcomeCtl
 * 
 */

@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	/**
	 * Contains display logic
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(System.getProperty("user.dir"));

        // Java 7
        System.out.println(Paths.get("").toAbsolutePath().toString());
		

		ServletUtility.forward(IMSView.WELCOME_VIEW, request, response);

	}

	
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return IMSView.WELCOME_VIEW;
	}

}
