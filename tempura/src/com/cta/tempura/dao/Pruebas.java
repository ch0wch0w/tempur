package com.cta.tempura.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.*;

public class Pruebas {
	public static void main(String[] args) throws Exception {
		EntityManager em = ConnectionManager.getEntityManager();
		
		// Tests de UserDAO
		//testUserDAO(em);
		// Tests de ExpenseDAO
		
		testExpenseDao(em);
		// Tests de ExpenseReportDAO
		
//		testExpenseReportDAO(em);
//		// Tests de NoteDAO
//		
//		testNoteDAO(em);
//		// Tests de ReimbursementDAO
//		
//		testReimbursementDAO(em);
		
		
		
		em.close();
	}
	
	// Tests de UserDAO
		public static void testUserDAO(EntityManager em){	
			System.out.println("***INICIO PRUEBAS USERDAO***");
			UserDAO userDAO = new UserDAO(em);
			System.out.println("-=Insert=-");
			em.getTransaction().begin();
			Integer expenseReportId = userDAO.insert(getUser(true));
			em.getTransaction().commit();
			System.out.println("La Id es "+ expenseReportId);	
			System.out.println("-=FindAll=-");
			List<User> expenseList = userDAO.findAll();
			System.out.println(expenseList.size());
			userDAO.delete(getUser(true));
			System.out.println("***FIN PRUEBAS USERDAO***");
		}
	
	
		// Tests de ExpenseDAO
	public static void testExpenseDao(EntityManager em){	
		System.out.println("***INICIO PRUEBAS EXPENSEDAO***");
		ExpenseDAO expenseDAO = new ExpenseDAO(em);
		System.out.println("-=Insert=-");
		Expense expense=new Expense();
		expense.setClaimer(getUser(false));
		expense.setName("TESTing");
		expense.setReimbursable(true);	
		expense.setAmount(new BigDecimal(100));
		expense.setExpenseReport(new ExpenseReport());
		em.getTransaction().begin();
		Integer expenseReportId = expenseDAO.insert(expense);
		em.getTransaction().commit();
		System.out.println("La Id es "+ expenseReportId);	
		System.out.println("-=FindAll=-");
		List<Expense> expenseList = expenseDAO.findAll();
		System.out.println(expenseList.size());	
		System.out.println("***FIN PRUEBAS EXPENSEDAO***");
		
	}
	
	// Tests de ExpenseReportDAO
	public static void testExpenseReportDAO(EntityManager em){	
		System.out.println("***Pruebas ExpenseReportDAO***");
		System.out.println("-=FindAll=-");
		ExpenseReportDAO expenseReportDAO = new ExpenseReportDAO(em);
		List<ExpenseReport> expenseList = expenseReportDAO.findAll();
		System.out.println(expenseList.size());
	}
	
	// Tests de NoteDAO
	public static void testNoteDAO(EntityManager em){	
		System.out.println("***Pruebas NoteDAO***");
		System.out.println("-=FindAll=-");
		NoteDAO noteDAO = new NoteDAO(em);
		List<Note> expenseList = noteDAO.findAll();
		System.out.println(expenseList.size());
	}
	// Tests de ReimbursementDAO
	public static void testReimbursementDAO(EntityManager em){	
		System.out.println("***Pruebas ReimbursementDAO***");
		System.out.println("-=FindAll=-");
		ReimbursementDAO reimbursementDAO = new ReimbursementDAO(em);
		List<Reimbursement> expenseList = reimbursementDAO.findAll();
		System.out.println(expenseList.size());
	}
	
	
	
	public static User getUser(boolean newUser){
		User user=new User();
		if (!newUser) {
			user.setUserId(1);
		}		
		user.setUserName("pepe");
		user.setUserRole(RoleType.MANAGER);		
		
		return user;
	}
	
	
			
}