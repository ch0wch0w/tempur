package com.cta.tempura.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionManager {
	
	private static ThreadLocal<EntityManager> localEntityManager = 
			new ThreadLocal<EntityManager>();
	
	
	private static EntityManagerFactory manager = null;
	
	public static EntityManager getEntityManager() {
		if (manager == null)
			manager = Persistence.createEntityManagerFactory("tempura-persistence-unit");
		if (localEntityManager.get()==null || localEntityManager.get().isOpen())
			localEntityManager.set(manager.createEntityManager());
		//return manager.createEntityManager();
		return localEntityManager.get();
	}

}
