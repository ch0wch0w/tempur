package com.cta.tempura.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionManager {
	
	private static EntityManagerFactory manager = null;
	
	public static EntityManager getEntityManager() {
		if (manager == null)
			manager = Persistence.createEntityManagerFactory("expenses-unit");
		return manager.createEntityManager();
	}

}
