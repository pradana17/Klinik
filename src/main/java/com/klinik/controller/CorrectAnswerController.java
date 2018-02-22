package com.klinik.controller;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.CorrectAnswerDAO;
import com.klinik.DAO.UserAnswerDAO;


@Controller
@RequestMapping("correctanswer")
public class CorrectAnswerController {
	
	@Autowired
	private CorrectAnswerDAO caDAO;
	private EntityManagerFactory factory;
	
	@GetMapping("/correctAnswer")
	public String index(Model model) {
		model.addAttribute("allCorrect",  caDAO.getAllCorrectAnswer());
		return "correctanswer/correctAnswer";
	}
	
	@GetMapping("/detailquiz/{questionid}")
	public String detailQuiz(Model model, @PathVariable("questionid") int Id) {
		model.addAttribute("objQuiz", caDAO.detailQuiz(Id));
		return "correctanswer/detailquiz";
	}
	


}
