package modal.dao;

import db.DB;
import modal.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDAO createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
}
