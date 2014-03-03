package com.cta.tempura.appservice;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;

import com.cta.tempura.dao.ExpenseReportDAO;
import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.ExpenseEvent;
import com.cta.tempura.model.ExpenseReport;
import com.cta.tempura.model.User;

/*
 * This class exposes operations to manipulate existing expense reports. A manager might modify details of a expense report, 
 * split it, clone it, or eliminate it 
 */
public class ManageExpenseReport {

	public ExpenseReport getExpenseReport(Integer expenseReportId) {
		EntityManager entityManager = ConnectionManager.getEntityManager();
		ExpenseReportDAO dao = new ExpenseReportDAO(entityManager);
		entityManager.getTransaction().begin();
		ExpenseReport expenseReport = dao.findById(expenseReportId);
		entityManager.getTransaction().commit();
		entityManager.close();
		return expenseReport;		
	}
	
	public Integer updateExpenseReport(ExpenseReport expenseReport) {
		EntityManager entityManager = ConnectionManager.getEntityManager();
		ExpenseReportDAO dao = new ExpenseReportDAO(entityManager);
		entityManager.getTransaction().begin();
		dao.update(expenseReport);
		entityManager.getTransaction().commit();
		entityManager.close();
		return expenseReport.getId();
	}
	
	public ExpenseReport removeExpenseReport(Integer expenseReportId) {
		EntityManager entityManager = ConnectionManager.getEntityManager();
		ExpenseReportDAO dao = new ExpenseReportDAO(entityManager);
		entityManager.getTransaction().begin();
		ExpenseReport expenseReport = dao.findById(expenseReportId);
		dao.remove(expenseReport);
		entityManager.getTransaction().commit();
		entityManager.close();
		return expenseReport;		
	}
	public static void main (String [] args) {
		//get expense report
		ManageExpenseReport mer = new ManageExpenseReport();
		System.out.println(mer.getExpenseReport(2));
		//update expense report
		ExpenseReport er = mer.getExpenseReport(2);
		er.setName(er.getName() + " V2.0");
		mer.updateExpenseReport(er);
		//delete expense report
		/*
		ExpenseReport er = mer.getExpenseReport(2);
		mer.removeExpenseReport(2);
		*/
	}
	
}
