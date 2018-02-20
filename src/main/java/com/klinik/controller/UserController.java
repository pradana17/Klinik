package com.klinik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.klinik.DAO.ChatDAO;

@Controller
public class UserController {

	@Autowired
	private ChatDAO chatDAO;
	
	@GetMapping("/detail/{senderId}")
	public String detail(Model model, @PathVariable("senderId") String id) {
		model.addAttribute("objChat", chatDAO.getChatId(id));
		return "viewremind";
	}
}
