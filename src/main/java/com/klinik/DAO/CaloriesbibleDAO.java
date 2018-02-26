package com.klinik.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Caloriesbible;

@Service
public class CaloriesbibleDAO {

	@Autowired
	private EntityManagerFactory factory;
	
	public List<Caloriesbible> getAllCaloriesbible(){
		return (List<Caloriesbible>) factory.createEntityManager().createQuery("from Caloriesbible where isactive = 1").getResultList();
	}
	
	public Caloriesbible getCaloriesbible(int idcal) {
		return (Caloriesbible) factory.createEntityManager().createQuery("from Caloriesbible where isactive = 1 and idcal = " + idcal).getSingleResult();
	}
	
	public boolean editCaloriesbible(Caloriesbible updatedCal) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaction = null;
		boolean isSuccess = true;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			Caloriesbible existingCal = (Caloriesbible) em.find(Caloriesbible.class, updatedCal.getIdcal());
			existingCal.setFoodname(updatedCal.getFoodname());
			existingCal.setCalorie(updatedCal.getCalorie());
			existingCal.setProtein(updatedCal.getProtein());
			existingCal.setFats(updatedCal.getFats());
			existingCal.setCarbhdrt(updatedCal.getCarbhdrt());
			existingCal.setCalcium(updatedCal.getCalcium());
			existingCal.setPhosphor(updatedCal.getPhosphor());
			existingCal.setIron(updatedCal.getIron());
			existingCal.setVita(updatedCal.getVita());
			existingCal.setVitb1(updatedCal.getVitb1());
			existingCal.setVitc(updatedCal.getVitc());
			existingCal.setAmount(updatedCal.getAmount());
			existingCal.setFweight(updatedCal.getFweight());
			existingCal.setIsactive(updatedCal.getIsactive());
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			isSuccess = false;
			System.out.println(ex.getMessage());
		}
		return isSuccess;
	}
	
	public boolean addCaloriesbible(Caloriesbible caloriesbible) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			caloriesbible.setIsactive(1);
			em.persist(caloriesbible);
			transaksi.commit();
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
		}
		return isSuccess;
	}
	
	public List<Caloriesbible> getFoodDetail(String nama) {
		return factory.createEntityManager().createQuery("from Caloriesbible where foodname like '%" + nama+"%'").getResultList();
	}	
}
