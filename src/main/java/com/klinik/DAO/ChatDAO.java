package com.klinik.DAO;

import java.util.Date;
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
	
	public List<Chat> getChatUser(String id) {
		return (List<Chat>) factory.createEntityManager().createQuery("from Chat where receiverId = '" + id + "'").getResultList();
	}
	
	public Chat getChatId(Integer id) {
		return (Chat) factory.createEntityManager().createQuery("from Chat where idchat = " + id).getSingleResult();
	}
	
	public boolean addChat(Chat chat) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			chat.setDatesending(new Date());
			chat.setDatereceiver(new Date());
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
	
	public boolean delChat(Chat chat) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			
			Chat existingChat = (Chat) em.find(Chat.class, chat.getIdchat());
			
			em.remove(existingChat);
			transaksi.commit();
			
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
			System.out.println(ex.getMessage());
		}
		return isSuccess;
	}
}
