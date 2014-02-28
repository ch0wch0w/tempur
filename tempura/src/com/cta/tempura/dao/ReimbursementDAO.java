package com.cta.tempura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.Reimbursement;


public class ReimbursementDAO {
	
	private EntityManager em;
	
	
	
	public ReimbursementDAO(EntityManager em) {
		super();
		this.em = em;
	}

	public Integer insert(Reimbursement reimbursement) {
		em.persist(reimbursement);
		return reimbursement.getId();
	}
	
	public Reimbursement findById(int id) {
		Reimbursement reimbursement = em.find(Reimbursement.class, id);
		return reimbursement;
		
	}
	
	public List<Reimbursement> findAll() {
		TypedQuery<Reimbursement> query = em.createQuery("FROM Reimbursement", Reimbursement.class);
		List<Reimbursement> reimbursements = query.getResultList();
		return reimbursements;
	}
	
	public void update(Reimbursement reimbursement) {
		em.merge(reimbursement);
	}
	
	public void remove(Reimbursement reimbursement) {
		em.remove(reimbursement);
	}
	
}
