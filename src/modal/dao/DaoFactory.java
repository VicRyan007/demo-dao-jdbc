package modal.dao;

import modal.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDAO createSellerDao() {
		return new SellerDaoJDBC();
	}
	
}
