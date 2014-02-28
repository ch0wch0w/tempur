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
	
	
	public Integer insert (ExpenseEvent event){
		em.persist(event);
		return event.getEventId();
	}
	
	public ExpenseEvent findById (int id){
		ExpenseEvent event = em.find(ExpenseEvent.class, id);
		return event;
	}
	
	public List<ExpenseEvent> findAll (){
		TypedQuery<ExpenseEvent> query = em.createQuery("FROM ExpenseEvent", ExpenseEvent.class);
		List<ExpenseEvent> events = query.getResultList();
		return events;
	}
	
	public void update (ExpenseEvent event){
		em.merge(event);
	}
	
	public void delete (ExpenseEvent event){
		em.remove(event);
	}
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


}
