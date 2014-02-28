package com.cta.tempura.appservice;

import java.util.List;

import javax.persistence.EntityManager;








import com.cta.tempura.dao.ExpenseReportDAO;
import com.cta.tempura.dao.UserDAO;
import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.ExpenseReport;
import com.cta.tempura.model.User;


public class startNewExpenseReportAppService {

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
	}
}
