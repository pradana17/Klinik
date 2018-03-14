package com.klinik.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.UserAnswerDAO;

@Controller
@RequestMapping("useranswer")
public class UserAnswerController {

	@Autowired
	private UserAnswerDAO userAnswerDAO;
	
	@GetMapping("/index")
	public String index(Model model, Principal principal) {
		model.addAttribute("allUserAnswers",  userAnswerDAO.getAllUserAnswer());
		String name = principal.getName();
	    model.addAttribute("username", name);
		return "useranswer/add";
	}	
}
