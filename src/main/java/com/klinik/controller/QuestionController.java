package com.klinik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.CorrectAnswerDAO;
import com.klinik.DAO.QuestionDAO;


@Controller
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	private QuestionDAO questionDAO;
	private CorrectAnswerDAO answerDAO;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("allQuestions",  questionDAO.getAllQuestion());
		return "question/index";
	}	
	
	@GetMapping("/detail/{questionid")
	public String detail(Model model, @PathVariable("questionid") short id) {
		model.addAttribute("objQuestion",questionDAO.getQuestion(id));
		return "question/detail";
	}

}
