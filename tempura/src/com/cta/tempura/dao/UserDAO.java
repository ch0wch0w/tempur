package com.cta.tempura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.db.ConnectionManager;
import com.cta.tempura.model.RoleType;
import com.cta.tempura.model.User;

public class UserDAO {
	
	private static UserDAO instance = null;

	private UserDAO() {
	}
	
	public static UserDAO getDAO() {
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}
	
	

	

	public Integer insert (User user){
		EntityManager em = ConnectionManager.getEntityManager();
		em.persist(user);
		return user.getUserId();
	}
	
	public User findById (int id){
		EntityManager em = ConnectionManager.getEntityManager();
		User user = em.find(User.class, id);
		return user;
	}
	
	public List<User> findAll (){
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<User> query = em.createQuery("FROM User", User.class);
		List<User> users = query.getResultList();
		// return em.createQuery("FROM User",User.class).getResultList(); //One liner
		return users;
		
	}
	
	public void update (User user){
		EntityManager em = ConnectionManager.getEntityManager();
		em.merge(user);
	}
	
	public void delete (User user){
		EntityManager em = ConnectionManager.getEntityManager();
		em.remove(user);
	}

	
	public static void main(String[] args){
		//populate database - inserts
		
		EntityManager em = ConnectionManager.getEntityManager();
		UserDAO dao = UserDAO.getDAO();
		
		em.getTransaction().begin();
		for (int i=0;i<=10;i++){
			User user = new User();
			//user.setUserId(i);
			user.setUserName("User "+i);
			user.setUserRole(RoleType.CLAIMER);
			dao.insert(user);
		}
		em.getTransaction().commit();
		em.close();
		
		//get all users
		/*
		EntityManager em = ConnectionManager.getEntityManager();
		UserDAO dao = new UserDAO(em);
		em.getTransaction().begin();
		List<User> staff= dao.findAll();
		em.getTransaction().commit();
		em.close();
		System.out.println(staff);
		*/
		//get by id
		/*
		EntityManager em = ConnectionManager.getEntityManager();
		UserDAO dao = new UserDAO(em);
		em.getTransaction().begin();
		User user = new User();
		user = dao.findById(5);
		em.getTransaction().commit();
		em.close();
		System.out.println(user);
		*/
		//updates
		/*EntityManager em = ConnectionManager.getEntityManager();
		UserDAO dao = new UserDAO(em);
		em.getTransaction().begin();
		User user = new User();
		user = dao.findById(6);
		user.setUserName("User6");
		//dao.update(user); //Not needed
		em.getTransaction().commit();
		em.close();
		System.out.println(user);
		*/
		//deletes
		/*
		EntityManager em = ConnectionManager.getEntityManager();
		UserDAO dao = new UserDAO(em);
		em.getTransaction().begin();
		User user = new User();
		user = dao.findById(11);
		dao.delete(user);
		em.getTransaction().commit();
		em.close();
		System.out.println(user);
		*/
		
	}
	
}
