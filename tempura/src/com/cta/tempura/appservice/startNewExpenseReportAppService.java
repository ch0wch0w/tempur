package com.cta.tempura.appservice;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;

import com.cta.tempura.dao.ExpenseReportDAO;
import com.cta.tempura.dao.UserDAO;
import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.Expense;
import com.cta.tempura.model.ExpenseEvent;
import com.cta.tempura.model.ExpenseReport;
import com.cta.tempura.model.User;


public class startNewExpenseReportAppService {

	public ExpenseReport startNewExpenseReport(ExpenseReport report, List<Expense>expenses) {
		return null;
	}
	
	public Integer startNewExpenseReport (User manager, ExpenseReport expenseReport){
		EntityManager em=ConnectionManager.getEntityManager();
		ExpenseReportDAO expenseReportDAO = new ExpenseReportDAO(em);
		expenseReport.setManager(manager);
		em.getTransaction().begin();
		Integer expenseReportId = expenseReportDAO.insert(expenseReport);
		em.getTransaction().commit();
		em.close();
		return expenseReportId;
	}
	
	public Integer startNewExpenseReport (Integer managerId, ExpenseReport expenseReport){
		User manager = new User();
		manager.setUserId(managerId);
		return startNewExpenseReport(manager, expenseReport);
	}
	
	public List<User> getStaff() {
		EntityManager em=ConnectionManager.getEntityManager();
		UserDAO userDAO= new UserDAO(em);
		em.getTransaction().begin();
		List<User> staff = userDAO.findAll();
		em.getTransaction().commit();
		em.close();
		return staff;
	}
	public void addClaimers(Integer expenseReportId, List<User> claimers){
		EntityManager em=ConnectionManager.getEntityManager();
		ExpenseReportDAO expenseReportDAO = new ExpenseReportDAO(em);
		em.getTransaction().begin();
		ExpenseReport report = expenseReportDAO.findById(expenseReportId);
		report.getUsers().addAll(claimers);
		expenseReportDAO.update(report);
		em.getTransaction().commit();
		em.close();
		//MessagingService.notify(claimers, MSG);
	}
	public static void main (String [] args) throws Exception {
		// Start new expense report
		startNewExpenseReportAppService test = new startNewExpenseReportAppService();
		User manager = new User();
		manager.setUserId(3);
		ExpenseReport expenseReport = new ExpenseReport();
		expenseReport.setName("J2EE UNIVERSITY");
		ExpenseEvent event = new ExpenseEvent();
		event.setEventId(1);
		expenseReport.setEvent(event);
		expenseReport.setStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2014/02/24"));
		expenseReport.setEndDate(new SimpleDateFormat("yyyy/MM/dd").parse("2014/03/07"));
		test.startNewExpenseReport(manager, expenseReport);
		List<User> claimants = test.getStaff();
		System.out.println(claimants);
		System.out.println(expenseReport);
		
		// Add claimants
		test.addClaimers(expenseReport.getId(), claimants);
	}
}
