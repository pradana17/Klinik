package com.klinik.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Mealplan;

@Service
public class MealplanDAO {
	
	@Autowired
	private EntityManagerFactory factory;
	
	public List<Mealplan> getAllMeal() {
		return (List<Mealplan>) factory.createEntityManager().createQuery("from Mealplan").getResultList();
	}
	
	public boolean addMeal(Mealplan meal) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			em.persist(meal);
			transaksi.commit();
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
//			log.error("DAO Error", ex.getMessage());
			//level logging 1 to 5: trace -> debug -> info -> warning -> error
		}
		return isSuccess;
	}
}
