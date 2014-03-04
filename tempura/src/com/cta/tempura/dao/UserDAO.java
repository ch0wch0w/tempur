package com.cta.tempura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.model.User;

public class UserDAO {
	
	private EntityManager em;
	
	public UserDAO(EntityManager em) {
		super();
		this.em=em;
	}

	

	public Integer insert (User user){
		
		em.persist(user);
		return user.getUserId();
	}
	
	public User findById (int id){
		User user = em.find(User.class, id);
		return user;
	}
	
	public List<User> findAll (){
		TypedQuery<User> query = em.createQuery("FROM User", User.class);
		List<User> users = query.getResultList();
		// return em.createQuery("FROM User",User.class).getResultList(); //One liner
		return users;
		
	}
	
	public void update (User user){
		em.merge(user);
	}
	
	public void delete (User user){
		em.remove(user);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	public static void main(String[] args){
		//populate database - inserts
		/*
		EntityManager em = ConnectionManager.getEntityManager();
		UserDAO dao = new UserDAO(em);
		
		em.getTransaction().begin();
		for (int i=0;i<=10;i++){
			User user = new User();
			//user.setUserId(i);
			user.setUserName("User "+i);
			user.setUserRole(RoleType.USER);
			dao.insert(user);
		}
		em.getTransaction().commit();
		em.close();
		*/
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
