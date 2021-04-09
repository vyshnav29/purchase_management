package in.co.inventor.mgt.sys.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import in.co.inventor.mgt.sys.bean.BaseBean;
import in.co.inventor.mgt.sys.bean.ProductBean;
import in.co.inventor.mgt.sys.exception.ApplicationException;
import in.co.inventor.mgt.sys.exception.DuplicateRecordException;
import in.co.inventor.mgt.sys.model.BrandModel;
import in.co.inventor.mgt.sys.model.CategoryModel;
import in.co.inventor.mgt.sys.model.ProductModel;
import in.co.inventor.mgt.sys.util.DataUtility;
import in.co.inventor.mgt.sys.util.DataValidator;
import in.co.inventor.mgt.sys.util.PropertyReader;
import in.co.inventor.mgt.sys.util.ServletUtility;


/**
 * Product functionality Controller. Performs operation for add, update,
 * delete and get Product
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

@WebServlet(name="ProductCtl",urlPatterns={"/ctl/ProductCtl"})
@MultipartConfig(maxFileSize = 16177215)
public class ProductCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log=Logger.getLogger(ProductCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	
	
	
	
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("ProductCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("price"))) {
            request.setAttribute("price",
                    PropertyReader.getValue("error.require", "Product Price"));
            pass = false;
        }
        
        
        if (DataValidator.isNull(request.getParameter("quantity"))) {
            request.setAttribute("quantity",
                    PropertyReader.getValue("error.require", "Quantity"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("date"))) {
            request.setAttribute("date",
                    PropertyReader.getValue("error.require", "Date"));
            pass = false;
        }
        

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description",
                    PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }
        
        
        if ("-----Select-----".equalsIgnoreCase(request.getParameter("brandId"))) {
			request.setAttribute("brandId", PropertyReader.getValue("error.require", "Brand Name"));
			pass = false;
		}

        if ("-----Select-----".equalsIgnoreCase(request.getParameter("categoryId"))) {
			request.setAttribute("categoryId", PropertyReader.getValue("error.require", "Category Name"));
			pass = false;
		}
        
        Part part = null;
		try {
			part = request.getPart("photo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

		if (DataValidator.isNull(fileName)) {
			request.setAttribute("photo", PropertyReader.getValue("error.require", "Image"));
			pass = false;
		}
        

        log.debug("ProductCtl validate method end");
        return pass;
    }

	@Override
	protected void preload(HttpServletRequest request) {
		try {
			List list=new BrandModel().list();
			List catlist=new CategoryModel().list();
			request.setAttribute("brandList",list);
			request.setAttribute("catList",catlist);
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
		log.debug("ProductCtl populateBean method start");
		ProductBean bean=new ProductBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBrandId(DataUtility.getLong(request.getParameter("brandId")));
		bean.setCategoryId(DataUtility.getLong(request.getParameter("categoryId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setQuantity(DataUtility.getLong(request.getParameter("quantity")));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		log.debug("ProductCtl populateBean method end");
		return bean;
	}
	
	@Override
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ProductCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       ProductModel model = new ProductModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            ProductBean bean;
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
	        log.debug("ProductCtl doGet method end");
    }

	/**
	 * Contains submit logic
	 */
	@Override
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("ProductCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		ProductModel model=new ProductModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			ProductBean bean=(ProductBean)populateBean(request);
				try {
					
					bean.setImage(ServletUtility.subImage(request, response, bean.getName()));
					
					if(id>0){
						
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(IMSView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(),
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
		ProductBean bean=	(ProductBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(IMSView.PRODUCT_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(IMSView.PRODUCT_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(IMSView.PRODUCT_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("ProductCtl doPost method end");
	}
	
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return IMSView.PRODUCT_VIEW;
	}
}