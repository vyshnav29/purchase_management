package in.co.inventor.mgt.sys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.inventor.mgt.sys.bean.ProductBean;
import in.co.inventor.mgt.sys.exception.ApplicationException;
import in.co.inventor.mgt.sys.exception.DatabaseException;
import in.co.inventor.mgt.sys.exception.DuplicateRecordException;
import in.co.inventor.mgt.sys.util.JDBCDataSource;

public class ProductModel {
	private static Logger log = Logger.getLogger(ProductModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM I_Product");
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
	
	

	public long add(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		ProductBean existbean = findByName(bean.getName());
		if (existbean != null) {
			throw new DuplicateRecordException("Product Id already exists");
		}
		bean.setBrandName(new BrandModel().findByPK(bean.getBrandId()).getName());
		bean.setCategoryName(new CategoryModel().findByPK(bean.getCategoryId()).getName());
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO I_Product VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2,bean.getBrandId());
			pstmt.setString(3, bean.getBrandName());
			pstmt.setLong(4,bean.getCategoryId());
			pstmt.setString(5, bean.getCategoryName());
			pstmt.setString(6, bean.getName());
			pstmt.setDate(7,new java.sql.Date(bean.getDate().getTime()));
			pstmt.setDouble(8, bean.getPrice());
			pstmt.setLong(9, bean.getQuantity());
			pstmt.setString(10, bean.getImage());
			pstmt.setString(11, bean.getDescription());
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
			throw new ApplicationException("Exception : Exception in add Product");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void delete(ProductBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM I_Product WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete Product");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public ProductBean findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM I_Product WHERE Name=?");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setBrandId(rs.getLong(2));
				bean.setBrandName(rs.getString(3));
				bean.setCategoryId(rs.getLong(4));
				bean.setCategoryName(rs.getString(5));
				bean.setName(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setPrice(rs.getDouble(8));
				bean.setQuantity(rs.getLong(9));
				bean.setImage(rs.getString(10));
				bean.setDescription(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Product by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	public ProductBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM I_Product WHERE ID=?");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setBrandId(rs.getLong(2));
				bean.setBrandName(rs.getString(3));
				bean.setCategoryId(rs.getLong(4));
				bean.setCategoryName(rs.getString(5));
				bean.setName(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setPrice(rs.getDouble(8));
				bean.setQuantity(rs.getLong(9));
				bean.setImage(rs.getString(10));
				bean.setDescription(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Product by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public void update(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		ProductBean beanExist = findByName(bean.getName());
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException(" Product is already exist");
		}
		bean.setBrandName(new BrandModel().findByPK(bean.getBrandId()).getName());
		bean.setCategoryName(new CategoryModel().findByPK(bean.getCategoryId()).getName());
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE I_Product SET brandId=?,brandName=?,categoryId=?,categoryName=?,NAME=?,date=?,price=?,quantity=?IMAGE=?,DESCRIPTION=?,"
							+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1,bean.getBrandId());
			pstmt.setString(2, bean.getBrandName());
			pstmt.setLong(3,bean.getCategoryId());
			pstmt.setString(4, bean.getCategoryName());
			pstmt.setString(5, bean.getName());
			pstmt.setDate(6,new java.sql.Date(bean.getDate().getTime()));
			pstmt.setDouble(7, bean.getPrice());
			pstmt.setLong(8, bean.getQuantity());
			pstmt.setString(9, bean.getImage());
			pstmt.setString(10, bean.getDescription());
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
			throw new ApplicationException("Exception in updating Product ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search(ProductBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(ProductBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM I_Product WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND Name like '" + bean.getName() + "%'");
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
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setBrandId(rs.getLong(2));
				bean.setBrandName(rs.getString(3));
				bean.setCategoryId(rs.getLong(4));
				bean.setCategoryName(rs.getString(5));
				bean.setName(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setPrice(rs.getDouble(8));
				bean.setQuantity(rs.getLong(9));
				bean.setImage(rs.getString(10));
				bean.setDescription(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Product");
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
		StringBuffer sql = new StringBuffer("select * from I_Product");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("sql in list Product :" + sql);
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setBrandId(rs.getLong(2));
				bean.setBrandName(rs.getString(3));
				bean.setCategoryId(rs.getLong(4));
				bean.setCategoryName(rs.getString(5));
				bean.setName(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setPrice(rs.getDouble(8));
				bean.setQuantity(rs.getLong(9));
				bean.setImage(rs.getString(10));
				bean.setDescription(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Products");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

	
}
