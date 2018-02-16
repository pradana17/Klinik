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

import com.klinik.DAO.CaloriesbibleDAO;

import com.klinik.model.Caloriesbible;

@Controller
@RequestMapping("caloriesbible")
public class CaloriesbibleController {

	@Autowired
	private CaloriesbibleDAO caloriesbibleDAO;
	
	@GetMapping("/index")
	public String index (Model model) {
		Caloriesbible cr = new Caloriesbible();
		model.addAttribute("semuaCaloriesbible",caloriesbibleDAO.getAllCaloriesbible());
		model.addAttribute("caloriesbible", cr);
		return ("caloriesbible/index");
	}
	
	@GetMapping("/detail/{idCal}")
	public String detail(Model model, @PathVariable("idCal") int idcal) {
		model.addAttribute("detailCalories",caloriesbibleDAO.getCaloriesbible(idcal));
		return ("caloriesbible/detail");
	}
	
	@PostMapping("/index")
	public String addCaloriesbible(@Valid Caloriesbible caloriesbible, BindingResult result) {
		if (!result.hasErrors() && caloriesbibleDAO.addCaloriesbible(caloriesbible)) {
			return "redirect:/caloriesbible/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "caloriesbible/index";
		}
	}
	
}
