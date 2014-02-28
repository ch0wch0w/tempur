package com.cta.tempura.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.Expense;

public class ExpenseDAO {
		
	private EntityManager em;
	
	public void insert(Expense expense) {		
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(expense);
		em.getTransaction().commit();
		em.close();
	}
	
	public Expense findById(Integer id) {
		EntityManager em = ConnectionManager.getEntityManager();
		Expense expense = em.find(Expense.class, id);
		em.close();
		return expense;
	}

	public List<Expense> findAll() {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Expense> query = em.createQuery("FROM Expense", Expense.class);
		List<Expense> expenses = query.getResultList();
		em.close();
		return expenses;
	}
	
	public void update(Expense expense) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(expense);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Expense expense) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(expense);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args) {
		ExpenseDAO dao = new ExpenseDAO();
		
		//System.out.println(dao.findById(1));
		
		System.out.println(dao.findAll());
	}
	
	
}
