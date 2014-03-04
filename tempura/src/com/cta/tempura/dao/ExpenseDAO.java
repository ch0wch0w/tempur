package com.cta.tempura.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import com.cta.tempura.model.Expense;

public class ExpenseDAO {
		
	private EntityManager em;
	
	
	
	public ExpenseDAO(EntityManager em) {
		super();
		this.em = em;
	}

	public Integer insert(Expense expense) {		
		em.persist(expense);
		return expense.getId();
	}
	
	public Expense findById(Integer id) {
		Expense expense = em.find(Expense.class, id);
		return expense;
	}

	public List<Expense> findAll() {
		TypedQuery<Expense> query = em.createQuery("FROM Expense", Expense.class);
		List<Expense> expenses = query.getResultList();
		return expenses;
	}
	
	public void update(Expense expense) {
		em.merge(expense);
	}
	
	public void remove(Expense expense) {
		em.remove(expense);
	}
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	
	
}
