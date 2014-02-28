package com.cta.tempura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.ExpenseEvent;

public class ExpenseEventDAO {
	
	private EntityManager em;
	
	public ExpenseEventDAO(EntityManager em) {
		super();
		this.em=em;
	}
	
	public ExpenseEventDAO() {
	}
	
	public void insert (ExpenseEvent event){
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(event);
		em.getTransaction().commit();
		em.close();
	}
	
	public ExpenseEvent findById (int id){
		EntityManager em = ConnectionManager.getEntityManager();
		ExpenseEvent event = em.find(ExpenseEvent.class, id);
		em.close();
		return event;
	}
	
	public List<ExpenseEvent> findAll (){
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<ExpenseEvent> query = em.createQuery("FROM ExpenseEvent", ExpenseEvent.class);
		List<ExpenseEvent> events = query.getResultList();
		em.close();
		return events;
	}
	
	public void update (ExpenseEvent event){
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(event);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete (ExpenseEvent event){
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(event);
		em.getTransaction().commit();
		em.close();
	}
	public static void main(String[] args) {
		ExpenseEventDAO eventDAO = new ExpenseEventDAO();
		
		//System.out.println(eventDAO.findById(1));
		System.out.println(eventDAO.findAll());
	}

}
