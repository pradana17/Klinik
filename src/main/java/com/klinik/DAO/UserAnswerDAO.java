package com.klinik.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Useranswers;

@Service
public class UserAnswerDAO {
	
	@Autowired
	private EntityManagerFactory factory;
	
	public List<Useranswers> getAllUserAnswer(){
		return factory.createEntityManager().createQuery("from Useranswers").getResultList();
	}
	
	public boolean addUserAnswer(Useranswers useranswer) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			useranswer.setDatetest(new Date());
			em.persist(useranswer);
			transaksi.commit();
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
			System.out.println(ex.getMessage());
		}
		return isSuccess;
	}	
}
