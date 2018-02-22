package com.klinik.controller;


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
	public String index (Model model) {
		model.addAttribute("semuaCaloriesbible",caloriesbibleDAO.getAllCaloriesbible());
		return ("patient/caloriesbible");
	}

}
