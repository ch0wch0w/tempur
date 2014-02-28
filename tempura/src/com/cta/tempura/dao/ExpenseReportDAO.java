package com.cta.tempura.dao;

import java.util.List;




import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.ExpenseReport;

public class ExpenseReportDAO {
	
	private EntityManager em;
	
	public ExpenseReportDAO(EntityManager em) {
		super();
		this.em=em;
	}

	public ExpenseReportDAO() {
		// TODO Auto-generated constructor stub
	}

	public Integer insert(ExpenseReport expenseReport) {		
		em.persist(expenseReport);
		return expenseReport.getId();
	}
	
	public ExpenseReport findById(int id) {
		ExpenseReport expenseReport = em.find(ExpenseReport.class, id);
		return expenseReport;
	}

	public List<ExpenseReport> findAll() {
		TypedQuery<ExpenseReport> query = em.createQuery("FROM ExpenseReport", ExpenseReport.class);
		List<ExpenseReport> expenseReports = query.getResultList();
		return expenseReports;
	}
	
	public void update(ExpenseReport expenseReport) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(expenseReport);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(ExpenseReport expenseReport) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(expenseReport);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args) {
		ExpenseReportDAO dao = new ExpenseReportDAO();
		
		//System.out.println(dao.findById(1));
		
		System.out.println(dao.findAll());
	}
}
