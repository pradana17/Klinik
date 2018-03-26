package com.klinik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.ChatDAO;

@Controller
@RequestMapping("chat")
public class ChatController {

	@Autowired
	private ChatDAO chatDAO;
	
	@GetMapping("/index")
	public String formChat(Model model) {
		model.addAttribute("semuaChat", chatDAO.getAllChat());
		return "chat/index";
	}
}
