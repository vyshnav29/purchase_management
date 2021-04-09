package in.co.inventor.mgt.sys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.inventor.mgt.sys.bean.BaseBean;
import in.co.inventor.mgt.sys.bean.InvoiceBean;
import in.co.inventor.mgt.sys.bean.InvoiceDetailBean;
import in.co.inventor.mgt.sys.exception.ApplicationException;
import in.co.inventor.mgt.sys.exception.DuplicateRecordException;
import in.co.inventor.mgt.sys.model.InvoiceDetailModel;
import in.co.inventor.mgt.sys.model.InvoiceModel;
import in.co.inventor.mgt.sys.model.ProductModel;
import in.co.inventor.mgt.sys.util.DataUtility;
import in.co.inventor.mgt.sys.util.DataValidator;
import in.co.inventor.mgt.sys.util.PropertyReader;
import in.co.inventor.mgt.sys.util.ServletUtility;

/**
 * Invoice functionality Controller. Performs operation for add, update, delete
 * and get Invoice
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */
@WebServlet(name = "InvoiceCtl", urlPatterns = { "/ctl/InvoiceCtl" })
public class InvoiceCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(InvoiceCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("InvoiceCtl validate method start");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("subTotal"))) {
			request.setAttribute("subTotal", PropertyReader.getValue("error.require", "Sub Total"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("paid"))) {
			request.setAttribute("paid", PropertyReader.getValue("error.require", "Paid"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("due"))) {
			request.setAttribute("due", PropertyReader.getValue("error.require", "Due"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "Quantity"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("paymentMethod"))) {
			request.setAttribute("paymentMethod", PropertyReader.getValue("error.require", "Payment Method"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("itemId"))) {
			request.setAttribute("itemId", PropertyReader.getValue("error.require", "Item Name"));
			pass = false;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("paymentMethod"))) {
			request.setAttribute("paymentMethod", PropertyReader.getValue("error.require", "Payment Name"));
			pass = false;
		}

		log.debug("InvoiceCtl validate method end");
		return pass;
	}

	@Override
	protected void preload(HttpServletRequest request) {
		try {
			List list = new ProductModel().list();
			request.setAttribute("prodList", list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("InvoiceCtl populateBean method start");
		InvoiceBean bean = new InvoiceBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setProductId(DataUtility.getLong(request.getParameter("itemId")));
		bean.setQuantity(DataUtility.getLong(request.getParameter("quantity")));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setOrderDate(DataUtility.getDate(request.getParameter("date")));
		bean.setCustomerName(DataUtility.getString(request.getParameter("name")));
		bean.setSubTotal(DataUtility.getDouble(request.getParameter("subTotal")));
		bean.setGst(DataUtility.getDouble(request.getParameter("gst")));
		bean.setPaid(DataUtility.getDouble(request.getParameter("paid")));
		bean.setDue(DataUtility.getDouble(request.getParameter("due")));
		bean.setPaidType(DataUtility.getString(request.getParameter("paymentMethod")));
		populateDTO(bean, request);
		log.debug("InvoiceCtl populateBean method end");
		return bean;
	}

	/**
	 * Contains display logic
	 */
	@Override
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("InvoiceCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		InvoiceModel model = new InvoiceModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			InvoiceBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("InvoiceCtl doGet method end");
	}

	/**
	 * Contains submit logic
	 */
	/**
	 *
	 */
	@Override
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("InvoiceCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		InvoiceModel model = new InvoiceModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {
			InvoiceBean bean = (InvoiceBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);

				} else {
					bean.setInvoiceNo(DataUtility.getRandomNum());
					long pk = model.add(bean);
					InvoiceDetailBean iBean=new InvoiceDetailBean();
					iBean.setInvoiceNo(bean.getInvoiceNo());
					iBean.setPrice(bean.getPrice());
					iBean.setProductId(bean.getProductId());
					iBean.setQuantity(bean.getQuantity());
					new InvoiceDetailModel().add(iBean);
					ServletUtility.setSuccessMessage("Data is successfully Saved", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.forward(IMSView.ERROR_VIEW, request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			InvoiceBean bean = (InvoiceBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(IMSView.INVOICE_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(IMSView.INVOICE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(IMSView.INVOICE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("InvoiceCtl doPost method end");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return IMSView.INVOICE_VIEW;
	}
}