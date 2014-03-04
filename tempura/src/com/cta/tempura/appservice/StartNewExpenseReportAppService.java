package com.cta.tempura.appservice;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.cta.tempura.dao.ExpenseDAO;
import com.cta.tempura.dao.ExpenseReportDAO;
import com.cta.tempura.dao.NoteDAO;
import com.cta.tempura.dao.UserDAO;
import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.Expense;
import com.cta.tempura.model.ExpenseEvent;
import com.cta.tempura.model.ExpenseReport;
import com.cta.tempura.model.Note;
import com.cta.tempura.model.User;


public class StartNewExpenseReportAppService {

	public ExpenseReport startNewExpenseReport(Integer managerId, Integer claimerId, Integer eventId, ExpenseReport report, List<Expense>expenses) {
		EntityManager em = ConnectionManager.getEntityManager();
		ExpenseReportDAO expenseReportDAO = new ExpenseReportDAO(em);
		ExpenseDAO expenseDAO = new ExpenseDAO(em);

		// Prepare the expense report before making it persistent
		User manager = new User();
		manager.setUserId(managerId);
		report.setManager(manager);
		ExpenseEvent event = new ExpenseEvent();
		event.setEventId(eventId);
		report.setEvent(event);

		// Add new claimer to the expense report
		User claimer = new User();
		claimer.setUserId(claimerId);
		List<User> claimants = new ArrayList<User>();
		claimants.add(claimer);
		report.setUsers(claimants);

		// Record new expense report
		em.getTransaction().begin();
		expenseReportDAO.insert(report);

		// Profit from persistence framework to record all receipts at once
		for (Expense expense : expenses) {
			expense.setExpenseReport(report);
			expense.setClaimer(claimer);
			expenseDAO.insert(expense);
		}

		em.getTransaction().commit();
		em.close();

		return report;
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
		/*StartNewExpenseReportAppService test = new StartNewExpenseReportAppService();
		User manager = new User();
		manager.setUserId(3);
		ExpenseReport expenseReport = new ExpenseReport();
		expenseReport.setName("J2EE UNIVERSITY");
		ExpenseEvent event = new ExpenseEvent();
		event.setEventId(1);
		expenseReport.setEvent(event);
		expenseReport.setStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2014/02/24"));
		expenseReport.setEndDate(new SimpleDateFormat("yyyy/MM/dd").parse("2014/03/07"));
		//test.startNewExpenseReport(manager, expenseReport);
		List<User> claimants = test.getStaff();
		System.out.println(claimants);
		//System.out.println(expenseReport);
		
		// Add claimants
		//test.addClaimers(expenseReport.getId(), claimants);
		*/
		// Start new batch expense report
		StartNewExpenseReportAppService batchClient = new StartNewExpenseReportAppService();

		ExpenseReport report = new ExpenseReport();
		report.setName(" Programa de Formación Java CTA Zaragoza");
		report.setStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2014/01/20"));
		report.setEndDate(new SimpleDateFormat("yyyy/MM/dd").parse("2014/02/05"));

		List<Expense> receipts = new ArrayList<Expense>();
		Expense taxi = new Expense();
		taxi.setName("Taxi Home-Sants");
		taxi.setAmount(new BigDecimal(8.60));
		taxi.setReimbursable(true);
		receipts.add(taxi);

		Expense meal = new Expense();
		meal.setName("Breakfast");
		meal.setAmount(new BigDecimal(4.20));
		meal.setReimbursable(true);
		receipts.add(meal);

		batchClient.startNewExpenseReport(1, 1, 1, report, receipts);
	}
}
