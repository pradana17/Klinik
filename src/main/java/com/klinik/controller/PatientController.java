package com.klinik.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.CaloriesbibleDAO;


@Controller
@RequestMapping("patient")
public class PatientController {
	
	@Autowired
	private CaloriesbibleDAO caloriesbibleDAO;
	
	@GetMapping("/caloriesbible")
	public String index (Model model, Principal principal) {
		model.addAttribute("semuaCaloriesbible",caloriesbibleDAO.getAllCaloriesbible());
		String name = principal.getName();
	    model.addAttribute("username", name);
		return ("patient/caloriesbible");
	}

}
