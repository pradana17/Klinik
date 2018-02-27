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

	@GetMapping("/index")
	public String index(Model model) {
		System.out.println("masuk 1");
		model.addAttribute("allQuestions", questionDAO.getAllQuestion());
		model.addAttribute("getUserAnswer", new Useranswers());
		return "question/index";
	}

	@PostMapping("/index")
	public String addAnswer(@Valid Useranswers useranswer, BindingResult result) {
		System.out.println("xxx " + result.hasErrors() + " " + userAnswerDAO.addUserAnswer(useranswer));
		System.out.println(useranswer.getUseranswersPK().getQuestionid());
		int id = useranswer.getUseranswersPK().getQuestionid();
		if (!result.hasErrors() && userAnswerDAO.addUserAnswer(useranswer)) {
			return "/question/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "/question/index/" + id;
		}
	}

	@GetMapping("/detail/{questionid}")
	public String detail(Model model, @PathVariable("questionid") Integer id) {
		model.addAttribute("objQuestion", questionDAO.getQuestion(id));
		Useranswers userAnswer = new Useranswers();
		model.addAttribute("getUserAnswer", userAnswer);
		return "question/detail";
	}

	@PostMapping("/detail")
	public String add(@Valid Useranswers useranswer, BindingResult result) {
		System.out.println("xxx " + result.hasErrors() + " " + userAnswerDAO.addUserAnswer(useranswer));

		if (!result.hasErrors() && userAnswerDAO.addUserAnswer(useranswer)) {
			return "question/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "/question/index";
		}
	}

	@GetMapping("/sum/{userpatient}")
	public String sum(Model model, @PathVariable("userpatient") String user) {
		model.addAttribute("sum", user);
		return "test/sum";
	}

	@PostMapping("/sum")
	public String sumResult(@Valid Personalitytest test, BindingResult result) {		
		System.out.println("test "+result.hasErrors()+" "+ userAnswerDAO.addTest(test));
		if(!result.hasErrors() && userAnswerDAO.addTest(test)) {
			return "redirect:/test/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return null;
		}
	}
}