package com.cta.tempura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.User;

public class UserDAO {
	
	private EntityManager em;
	
	public UserDAO(EntityManager em) {
		super();
		this.em=em;
	}

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	public void insert (User user){
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
	
	public User findById (int id){
		EntityManager em = ConnectionManager.getEntityManager();
		User user = em.find(User.class, id);
		em.close();
		return user;
	}
	
	public List<User> findAll (){
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<User> query = em.createQuery("FROM User", User.class);
		List<User> users = query.getResultList();
		em.close();
		return users;
	}
	
	public void update (User user){
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete (User user){
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		
		//System.out.println(userDAO.findById(1));
		System.out.println(userDAO.findAll());
	}
}
