package com.klinik.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Chat;


@Service
public class ChatDAO {
	
	@Autowired
	private EntityManagerFactory factory;
	
	public List<Chat> getAllChat() {
		return (List<Chat>) factory.createEntityManager().createQuery("from Chat").getResultList();
	}
	
	public List<Chat> getChatId(String id) {
		return (List<Chat>) factory.createEntityManager().createQuery("from Chat where receiverId = '" + id + "'").getResultList();
	}
	
	public boolean addChat(Chat chat) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			em.persist(chat);
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
