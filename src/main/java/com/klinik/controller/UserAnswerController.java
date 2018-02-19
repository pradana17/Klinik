package com.klinik.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.CorrectAnswerDAO;
import com.klinik.DAO.QuestionDAO;
import com.klinik.DAO.UserAnswerDAO;
import com.klinik.model.Useranswers;

@Controller
@RequestMapping("useranswer")
public class UserAnswerController {

	@Autowired
	private UserAnswerDAO userAnswerDAO;
	private CorrectAnswerDAO answerDAO;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("allUserAnswers",  userAnswerDAO.getAllUserAnswer());
		return "useranswer/index";
	}	
	
	@GetMapping("/detail")
	public String addForm(Model model) {
		Useranswers userAnswer = new Useranswers();
		model.addAttribute("getUserAnswer", userAnswer);
		model.addAttribute("getCaloriesNeed", answerDAO.getCaloriesNeed(userAnswer.getChoosenanswerid(), userAnswer.getQuestion().getQuestionid()));
		return "/useranswer/add";
	}
	
	@PostMapping("/detail")
	public String addActor(@Valid Useranswers useranswer, BindingResult result) {		
		if(!result.hasErrors() && userAnswerDAO.addUserAnswer(useranswer)) {
			return "redirect:/useranswer/add";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "/question/index";
		}
	}
}
