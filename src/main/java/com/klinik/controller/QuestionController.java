package com.klinik.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.CorrectAnswerDAO;
import com.klinik.DAO.QuestionDAO;
import com.klinik.DAO.UserAnswerDAO;
import com.klinik.model.Personalitytest;
import com.klinik.model.Useranswers;


@Controller
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	private QuestionDAO questionDAO;	
	@Autowired
	private UserAnswerDAO userAnswerDAO;
	@Autowired
	CorrectAnswerDAO correctDAO;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("allQuestions",  questionDAO.getAllQuestion());
//		Useranswers userAnswer = new Useranswers();
//		model.addAttribute("getUserAnswer", userAnswer);
		return "question/index";
	}	
	
	@GetMapping("/detail/{questionid}")
	public String detail(Model model, @PathVariable("questionid") Integer id) {
		model.addAttribute("objQuestion",questionDAO.getQuestion(id));
		Useranswers userAnswer = new Useranswers();
		model.addAttribute("getUserAnswer", userAnswer);
//		model.addAttribute("getNutrition",correctDAO.getCaloriesNeed(userAnswer.getChoosenanswerid(), id));
//		System.out.println("cek k: "+userAnswer.getChoosenanswerid()+" "+id);
		return "question/detail";
	}
	
	
	@PostMapping("/detail")
	public String addAnswer(@Valid Useranswers useranswer, BindingResult result) {		
		System.out.println("test "+result.hasErrors()+" "+userAnswerDAO.addUserAnswer(useranswer));
	
		if(!result.hasErrors() && userAnswerDAO.addUserAnswer(useranswer)) {
			return "redirect:/question/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "/question/index";
		}
	}
}