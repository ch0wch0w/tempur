package com.cta.tempura.dao;

import java.util.List;




import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.ExpenseReport;

public class ExpenseReportDAO {
	
	private EntityManager entityManager;
	
	public ExpenseReportDAO(EntityManager em) {
		super();
		this.entityManager=em;
	}

	public ExpenseReportDAO() {
		// TODO Auto-generated constructor stub
	}

	public void insert(ExpenseReport expenseReport) {		
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(expenseReport);
		em.getTransaction().commit();
		em.close();
	}
	
	public ExpenseReport findById(int id) {
		EntityManager em = ConnectionManager.getEntityManager();
		ExpenseReport expenseReport = em.find(ExpenseReport.class, id);
		em.close();
		return expenseReport;
	}

	public List<ExpenseReport> findAll() {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<ExpenseReport> query = em.createQuery("FROM ExpenseReport", ExpenseReport.class);
		List<ExpenseReport> expenseReports = query.getResultList();
		em.close();
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
