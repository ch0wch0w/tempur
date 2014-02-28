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
	
}
