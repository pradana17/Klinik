package com.klinik.controller;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.CaloriesbibleDAO;
import com.klinik.DAO.PatientDAO;
import com.klinik.model.Patient;


@Controller
@RequestMapping("patient")
public class PatientController {
	
	@Autowired
	private CaloriesbibleDAO caloriesbibleDAO;
	
	@Autowired
	private PatientDAO patDAO;
	
	@GetMapping("/caloriesbible")
	public String index (Model model) {
		model.addAttribute("semuaCaloriesbible",caloriesbibleDAO.getAllCaloriesbible());
		
		return ("patient/caloriesbible");
	}
	
	@GetMapping("/index")
	public String formPat(Model model) {
		Patient patient  = new Patient();
		model.addAttribute("semuaPat", patDAO.getAllPatient());
		model.addAttribute("pat", patient);
	
		return "patient/index";
	}
	
	@PostMapping("/index")
	public String addPatient(@Valid Patient patient, BindingResult result) {
		
		if(!result.hasErrors() && patDAO.addPatient(patient)) {
			return "redirect:/patient/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "patient/index";
		}
	}

	
	@GetMapping("/detailpat/{userpatient}")
    public String viewPat(@PathVariable("userpatient") String user, ModelMap modelMap) {
        Patient patient = patDAO.getPatientUser(user);
        modelMap.addAttribute("objPatient", patient);
        return "patient/index :: viewpat";
    }
}
