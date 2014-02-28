package com.cta.tempura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.Reimbursement;


public class ReimbursementDAO {
	public void insert(Reimbursement reimbursement) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(reimbursement);
		em.getTransaction().commit();
		em.close();
	}
	
	public Reimbursement findById(int id) {
		EntityManager em = ConnectionManager.getEntityManager();
		Reimbursement reimbursement = em.find(Reimbursement.class, id);
		em.close();
		return reimbursement;
		
	}
	
	public List<Reimbursement> findAll() {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Reimbursement> query = em.createQuery("FROM Reimbursement", Reimbursement.class);
		List<Reimbursement> reimbursements = query.getResultList();
		em.close();
		return reimbursements;
	}
	
	public void update(Reimbursement reimbursement) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(reimbursement);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void remove(Reimbursement reimbursement) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(reimbursement);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args) {
		ReimbursementDAO dao = new ReimbursementDAO();
		for (Reimbursement emp: dao.findAll())
			System.out.println(emp);
	}
}
