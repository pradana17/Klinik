package com.klinik.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.CorrectAnswerDAO;


@Controller
@RequestMapping("correctanswer")
public class CorrectAnswerController {
	
	@Autowired
	private CorrectAnswerDAO caDAO;
	
	@GetMapping("/correctAnswer")
	public String index(Model model, Principal principal) {
		model.addAttribute("allCorrect",  caDAO.getAllCorrectAnswer());
		String name = principal.getName();
	    model.addAttribute("username", name);
		return "correctanswer/correctAnswer";
	}
	
	@GetMapping("/detailquiz/{questionid}")
	public String detailQuiz(Model model, @PathVariable("questionid") int Id, Principal principal) {
		model.addAttribute("objQuiz", caDAO.detailQuiz(Id));
		String name = principal.getName();
	    model.addAttribute("username", name);
		return "correctanswer/detailquiz";
	}
	


}
