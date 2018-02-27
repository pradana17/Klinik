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

import com.klinik.DAO.TestDAO;
import com.klinik.model.Personalitytest;
import com.klinik.model.Useranswers;

@Controller
@RequestMapping("test")
public class PersonalityTestController {

	@Autowired
	private TestDAO testDAO;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("allTest",  testDAO.getAllTest());
		return "test/index";
	}	
	
	@GetMapping("/sum/{userpatient}")
	public String sum(Model model, @PathVariable("userpatient") String user) {
		model.addAttribute("sum",  user);
		return "test/sum";
	}
	
	@PostMapping("/sum")
	public String sumResult(@Valid Personalitytest test, BindingResult result) {		
		System.out.println("test "+result.hasErrors()+" "+testDAO.addTest(test));
		if(!result.hasErrors() && testDAO.addTest(test)) {
			return "redirect:/test/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return null;
		}
	}
}

