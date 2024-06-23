package modal.dao;

import java.util.List;

import modal.entities.Department;
import modal.entities.Seller;

public interface SellerDAO {

	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
	List<Seller> findByDepartment(Department department);
	
}
