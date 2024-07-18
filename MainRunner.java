package org.jsp.DemoProject;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainRunner {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	static Scanner scanner = new Scanner(System.in);
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction transaction = em.getTransaction(); 
	
	
	public static void main(String[] args) {
		
		System.out.print("hey hello ! \n  \n 1.User \n 2.Product \n Choose your operations User or Product : ");
		int user = 1;
		int product = 2;
		int option = scanner.nextInt();
		
		if(user == option) {
			System.out.print("\n1.Create a user \n 2.Display user \n 3.Update a user \n 4.Delete a user \n Choose your operation : ");
			int userInput = scanner.nextInt();
			
			switch (userInput ) {
			case 1: createNewUser();
					break;
			case 2 : displayUser();
					break;
			case 3 : updateUser();
					break;
			case 4 : deleteUser();
					break;
			default:
				break;
			}

			
			
		}else if(product == option){
			System.out.print("\n1.Create a product \n 2.Display product 3.Update a product \n 4.Delete a product \n Choose your operation : ");
            int productInput = scanner.nextInt();
			
			switch (productInput ) {
			case 1: createNewProduct();
					break;
			case 2 : displayProduct();
					break;
			case 3 : updateProduct();
					break;
			case 4 : deleteProduct();
					break;
			default:
				break;
			}

			
		}
		
	
		
	}
	
	static void createNewUser() {
			try {
				// Start the transaction
				transaction.begin();
				
				//To Create a user data into the DB
				User user = new User();
//				user.setId(1);
//				user.setName("Uday");
//				user.setEmail("uday@gmail.com");
//				user.setPhone(9731867223l);
//				user.setPassword("abc");
				
				
				
				//taking the data dynamically
				System.out.print("Enter ID: ");
				user.setId(scanner.nextInt());

				System.out.print("Enter Name: ");
				user.setName(scanner.next());

				System.out.print("Enter Email: ");
				user.setEmail(scanner.next());

				System.out.print("Enter Phone: ");
				user.setPhone(scanner.nextLong());

				System.out.print("Enter Password: ");
				user.setPassword(scanner.next());
				//this pushes the data to the DB
				em.persist(user);
				
				// Commit the transaction
				transaction.commit();

				System.out.println("Data saved to database!");
			} catch (Exception ex) {
				if (transaction != null && transaction.isActive()) {
					transaction.rollback();
				}
				ex.printStackTrace();
			} finally {
				scanner.close();
				em.close();
				emf.close();
			}
		
	}
	
	static void displayUser() {
		System.out.print("Enter the user ID to display : ");
		User user = em.find(User.class, scanner.nextInt());
		System.out.println(user);

		
	}
	
	static void updateUser() {
		System.out.print("Enter the user Id to change : ");
		User user = em.find(User.class, scanner.nextInt());
//		System.out.println(p.getName());
		System.out.print("Enter the user name to Update : ");
		user.setName(scanner.next());
		transaction.begin();
		em.merge(user);
		transaction.commit();
		System.out.println("Product name updated : "+user.getName());
		
	}
	
	static void deleteUser() {
		System.out.print("Enter the user ID to delete : ");
		User user = em.find(User.class, scanner.nextInt());
		transaction.begin();
		em.remove(user);
		System.out.println("Item Deleted !");
		transaction.commit();
		
	}
	
	static void createNewProduct() {
		try {
			Products pd = new Products();
//			pd.setId(2);
//			pd.setName("SAMSUNG");
//			pd.setPrice(17000);
//			pd.setQuantity(28);
			
			
			//taking the data dynamically
			System.out.print("Enter ID: ");
			pd.setId(scanner.nextInt());

			System.out.print("Enter Name: ");
			pd.setName(scanner.next());

			System.out.print("Enter Price: ");
			pd.setPrice(scanner.nextInt());

			System.out.print("Enter Quantity: ");
			pd.setQuantity(scanner.nextInt());
			
			// Start the transaction
			transaction.begin();

			//this pushes the data to the DB
			em.persist(pd);
			
			// Commit the transaction
			transaction.commit();

			System.out.println("Data saved to database!");
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			scanner.close();
			em.close();
			emf.close();
		}
	
		
	}
	
	static void displayProduct() {
		System.out.println("Enter the product ID to display : ");
		Products p = em.find(Products.class, scanner.nextInt());
		System.out.println(p);

		
	}
	
	static void updateProduct() {
		System.out.println("Enter the Id to change : ");
		Products p = em.find(Products.class, scanner.nextInt());
//		System.out.println(p.getName());
		System.out.println("Enter the name to Update : ");
		p.setName(scanner.next());
		transaction.begin();
		em.merge(p);
		transaction.commit();
		System.out.println("Product name updated : "+p.getName());
		
	}
	
	static void deleteProduct() {
		System.out.print("Enter the item ID to delete : ");
		Products p = em.find(Products.class, scanner.nextInt());
		transaction.begin();
		em.remove(p);
		System.out.println("Item Deleted !");
		transaction.commit();
		
	}
	
}
