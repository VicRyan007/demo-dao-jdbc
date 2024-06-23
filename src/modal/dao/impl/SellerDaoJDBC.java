package modal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import modal.dao.SellerDAO;
import modal.entities.Department;
import modal.entities.Seller;

public class SellerDaoJDBC implements SellerDAO {

	private Connection con;
	
	public SellerDaoJDBC (Connection con) {
		this.con = con;
	}
	
	@Override
	public void insert(Seller obj) {

		PreparedStatement st = null;
		try {
			st = con.prepareStatement(
				"INSERT INTO seller "
				+ "(Name,Email,BirthDate,BaseSalary,DepartmentId"
				+ "VALUES"
				+ "(?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS
			);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()) );
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
				
			}else {
				throw new DbException("Unexpected erro ! no rows affected ");
			}
					
		}catch ( SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement st  = null;
		ResultSet rs = null;
		try {
			
			st = con.prepareStatement(
				"SELECT seller.*,department.Name as DepName"
				+ "FROM seller INNER JOIN department"
				+ "ON seller.DepartmentId = department.Id"
				+ "WHERE seller.id = ?"
			);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				Seller obj = instantiateDepartment(rs,dep);
				return obj;
			}
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}

	private Seller instantiateDepartment(ResultSet rs, Department dep) throws SQLException{
		 Seller obj = new Seller();
			obj.setId(rs.getInt("Id"));
			obj.setEmail(rs.getString("Email"));
			obj.setBaseSalary(rs.getDouble("BaseSalary"));
			obj.setBirthDate(rs.getDate("BirthDate"));
			obj.setDepartment(dep);
			return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException  {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st  = null;
		ResultSet rs = null;
		try {
			
			st = con.prepareStatement(
				"SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "ORDER BY Name"
			);
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer,Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep ==null) {
					dep  = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"),dep);
				}
				
				Seller obj = instantiateDepartment(rs,dep);
				list.add(obj);
			}
			return list;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {

		PreparedStatement st  = null;
		ResultSet rs = null;
		try {
			
			st = con.prepareStatement(
				"SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "WHERE DepartmentId = 2 "
				+ "ORDER BY Name"
			);
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer,Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep ==null) {
					dep  = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"),dep);
				}
				
				Seller obj = instantiateDepartment(rs,dep);
				list.add(obj);
			}
			return list;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	
}
