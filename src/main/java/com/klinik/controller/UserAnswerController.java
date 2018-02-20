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
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("allUserAnswers",  userAnswerDAO.getAllUserAnswer());
		return "useranswer/add";
	}	
	

}
