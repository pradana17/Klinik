package com.klinik.api;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.DAO.ChatDAO;
import com.klinik.model.Chat;

@RestController
@RequestMapping("/api/chat")
public class ChatAPI {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	
	@Autowired
	private ChatDAO chatDAO;
	
	
	@GetMapping("/addchat")
	public boolean addChat(
			@RequestParam("user") String id,
			@RequestParam("mes") String mes) {

		Chat chat = new Chat();
		chat.setReceiverId(id);
		chat.setMessage(mes);
		chat.setSenderId("Admin");
		
		return chatDAO.addChat(chat);
	}
	
	@GetMapping("/delchat")
	public boolean delChat(@RequestParam("id") Integer id) {
		Chat chat = chatDAO.getChatId(id);
		return chatDAO.delChat(chat);
	}
	
}
