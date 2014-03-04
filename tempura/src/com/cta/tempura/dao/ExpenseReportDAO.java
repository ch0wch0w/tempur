package com.cta.tempura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.model.ExpenseReport;

public class ExpenseReportDAO {
	
	private EntityManager em;
	
	public ExpenseReportDAO(EntityManager em) {
		super();
		this.em=em;
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
		em.merge(expenseReport);
	}
	
	public void remove(ExpenseReport expenseReport) {
		em.remove(expenseReport);
	}
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	
}
