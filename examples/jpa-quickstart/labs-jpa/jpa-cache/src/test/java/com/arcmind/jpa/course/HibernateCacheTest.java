package com.arcmind.jpa.course;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.TestCase;

public class HibernateCacheTest extends TestCase {

	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;

	protected void setUp() throws Exception {
		/* Use Persistence.createEntityManagerFactory to create 
		 * "security-domain" persistence unit. */ 
		
		entityManagerFactory = Persistence.createEntityManagerFactory("security-domain");
		deleteTestData();
		createTestData();

	}
	

	protected void tearDown() throws Exception {
		deleteTestData();
		if(entityManager.isOpen()) {
			entityManager.close();
		}
	}
	
	public void testQueryUsers () throws Exception {

        try {

        	//TODO: Create entity manager from factory

        	//TODO: Create query that loads all the users

        	//TODO: Run the query, stuffing the results into a list.

        	//TODO: Assert the users are not null

        	//TODO: assert the users .size > 0

        	//TODO: add a query to load the first user in the list

        	//TODO: Assert the user loaded successfully.
        	
        } catch (Exception ex) {
        	ex.printStackTrace();
        	throw ex;
        }
	}
	
	private void deleteTestData() throws Exception {
		
		entityManager = entityManagerFactory.createEntityManager();
		/* Start a transaction. */
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

			Query query = entityManager.createQuery("delete Phone");
			query.executeUpdate();
			query = entityManager.createQuery("delete User");
			query.executeUpdate();
        	transaction.commit();
        	
        } catch (Exception ex) {
        	ex.printStackTrace();
        	transaction.rollback();
        	throw ex;
        }
		
	}

	private void createTestData() throws Exception {
		System.out.println("CREATE CALLED");
		String[] userNames = new String[]{"RickHi","BobSmith","Sergey","PaulHix","Taboraz"}; 
		entityManager = entityManagerFactory.createEntityManager();
		/* Start a transaction. */
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
        	for (String userName : userNames) {
        		User user = new User();
        		user.setName(userName);
        		entityManager.persist(user);
        	}
        	for (int index = 0; index < 100; index++) {
        		if (index % 10 == 0) {
        			entityManager.flush(); //Flush data to the database
        		}
        		User user = new User();
        		user.setName("user" + index);
        		entityManager.persist(user);
        	}        	
        	transaction.commit();
        	
        } catch (Exception ex) {
        	ex.printStackTrace();
        	transaction.rollback();
        	throw ex;
        }
	}

}