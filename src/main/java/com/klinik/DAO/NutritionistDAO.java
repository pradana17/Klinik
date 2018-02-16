package com.klinik.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Nutritionist;


@Service
public class NutritionistDAO {

	@Autowired
	private EntityManagerFactory factory;
	
	public List<Nutritionist> getAllNutritionist() {
		return (List<Nutritionist>) factory.createEntityManager().createQuery("from Nutritionist").getResultList();
	}
	
	public Nutritionist getNutrionUser(String user) {
		return (Nutritionist) factory.createEntityManager().createQuery("from Nutritionist where usernutritionist = ' + user'").getSingleResult();
	}
	
	public boolean addNutritionist(Nutritionist nutritionist) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			em.persist(nutritionist);
			transaksi.commit();
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
//			log.error("DAO Error", ex.getMessage());
			//level logging 1 to 5: trace -> debug -> info -> warning -> error
		}
		return isSuccess;
	}
	
	public boolean editNutritionist(Nutritionist nutritionist) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			
			Nutritionist existingNutrion = (Nutritionist) em.find(Nutritionist.class, nutritionist.getUsernutritionist());
			
			existingNutrion.setFullname(nutritionist.getFullname());
			existingNutrion.setPassword(nutritionist.getPassword());
			existingNutrion.setEmail(nutritionist.getEmail());
			//existingNutrion.setIsactive(nutritionist.getIsactive());
			
			transaksi.commit();
			
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
			System.out.println(ex.getMessage());
		}
		return isSuccess;
	}
}
