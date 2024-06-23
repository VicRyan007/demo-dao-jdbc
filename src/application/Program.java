package application;

import java.util.Date;

import modal.dao.DaoFactory;
import modal.dao.SellerDAO;
import modal.entities.Department;
import modal.entities.Seller;

public class Program {

	public static void main(String[]args) {
		
		SellerDAO sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		
	}
} 
