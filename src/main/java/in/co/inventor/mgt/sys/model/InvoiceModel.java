package in.co.inventor.mgt.sys.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import in.co.inventor.mgt.sys.bean.InvoiceBean;
import in.co.inventor.mgt.sys.exception.ApplicationException;
import in.co.inventor.mgt.sys.exception.DatabaseException;
import in.co.inventor.mgt.sys.exception.DuplicateRecordException;
import in.co.inventor.mgt.sys.util.JDBCDataSource;

public class InvoiceModel {
	private static Logger log = Logger.getLogger(InvoiceModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM I_Invoice");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	

	public long add(InvoiceBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO I_Invoice VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2,bean.getInvoiceNo());
			pstmt.setString(3, bean.getCustomerName());
			pstmt.setDate(4,new java.sql.Date(bean.getOrderDate().getTime()));
			pstmt.setDouble(5, bean.getSubTotal());
			pstmt.setDouble(6, bean.getGst());
			pstmt.setDouble(7, bean.getDiscount());
			pstmt.setDouble(8, bean.getNetTotal());
			pstmt.setDouble(9, bean.getPaid());
			pstmt.setDouble(10, bean.getDue());
			pstmt.setString(11, bean.getPaidType());
			pstmt.setString(12, bean.getCreatedBy());
			pstmt.setString(13, bean.getModifiedBy());
			pstmt.setTimestamp(14, bean.getCreatedDatetime());
			pstmt.setTimestamp(15, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Invoice");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void delete(InvoiceBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM I_Invoice WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Invoice");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public InvoiceBean findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM I_Invoice WHERE Name=?");
		InvoiceBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new InvoiceBean();
				bean.setId(rs.getLong(1));
				bean.setInvoiceNo(rs.getLong(2));
				bean.setCustomerName(rs.getString(3));
				bean.setOrderDate(rs.getDate(4));
				bean.setSubTotal(rs.getDouble(5));
				bean.setGst(rs.getDouble(6));
				bean.setDiscount(rs.getDouble(7));
				bean.setNetTotal(rs.getDouble(8));
				bean.setPaid(rs.getDouble(9));
				bean.setDue(rs.getDouble(10));
				bean.setPaidType(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Invoice by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	public InvoiceBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM I_Invoice WHERE ID=?");
		InvoiceBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new InvoiceBean();
				bean.setId(rs.getLong(1));
				bean.setInvoiceNo(rs.getLong(2));
				bean.setCustomerName(rs.getString(3));
				bean.setOrderDate(rs.getDate(4));
				bean.setSubTotal(rs.getDouble(5));
				bean.setGst(rs.getDouble(6));
				bean.setDiscount(rs.getDouble(7));
				bean.setNetTotal(rs.getDouble(8));
				bean.setPaid(rs.getDouble(9));
				bean.setDue(rs.getDouble(10));
				bean.setPaidType(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Invoice by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public void update(InvoiceBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE I_Invoice SET InvoiceNo=?,customerName=?,orderDate=?,subTotal=?,gst=?,disscout=?,netTotal=?,paid=?,due=?,paidType=?,"
							+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1,bean.getInvoiceNo());
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setDate(3,new java.sql.Date(bean.getOrderDate().getTime()));
			pstmt.setDouble(4, bean.getSubTotal());
			pstmt.setDouble(5, bean.getGst());
			pstmt.setDouble(6, bean.getDiscount());
			pstmt.setDouble(7, bean.getNetTotal());
			pstmt.setDouble(8, bean.getPaid());
			pstmt.setDouble(9, bean.getDue());
			pstmt.setString(10, bean.getPaidType());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			pstmt.setLong(15, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Invoice ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search(InvoiceBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(InvoiceBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM I_Invoice WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			
			if (bean.getInvoiceNo() > 0) {
				sql.append(" AND InvoiceNo = " + bean.getInvoiceNo());
			}
			
			if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
				sql.append(" AND CustomerName like '" + bean.getCustomerName() + "%'");
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new InvoiceBean();
				bean.setId(rs.getLong(1));
				bean.setInvoiceNo(rs.getLong(2));
				bean.setCustomerName(rs.getString(3));
				bean.setOrderDate(rs.getDate(4));
				bean.setSubTotal(rs.getDouble(5));
				bean.setGst(rs.getDouble(6));
				bean.setDiscount(rs.getDouble(7));
				bean.setNetTotal(rs.getDouble(8));
				bean.setPaid(rs.getDouble(9));
				bean.setDue(rs.getDouble(10));
				bean.setPaidType(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Invoice");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from I_Invoice");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("sql in list Invoice :" + sql);
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				InvoiceBean bean = new InvoiceBean();
				bean.setId(rs.getLong(1));
				bean.setInvoiceNo(rs.getLong(2));
				bean.setCustomerName(rs.getString(3));
				bean.setOrderDate(rs.getDate(4));
				bean.setSubTotal(rs.getDouble(5));
				bean.setGst(rs.getDouble(6));
				bean.setDiscount(rs.getDouble(7));
				bean.setNetTotal(rs.getDouble(8));
				bean.setPaid(rs.getDouble(9));
				bean.setDue(rs.getDouble(10));
				bean.setPaidType(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Invoices");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

	
}
