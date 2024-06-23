package application;

import java.util.Date;

import modal.dao.DaoFactory;
import modal.dao.SellerDAO;
import modal.entities.Department;
import modal.entities.Seller;

public class Program {

	public static void main(String[]args) {
		
		Department obj = new Department(1,"books");
		System.out.println(obj);
		
		Seller seller = new Seller(21,"Bob","bob@gmail.com",new Date(),3000.0,obj);
		
		SellerDAO sellerDao = DaoFactory.createSellerDao();
		
		System.out.println(seller);
		
		
	}
}
