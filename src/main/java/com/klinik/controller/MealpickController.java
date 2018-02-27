package com.klinik.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.MealpickDAO;

@Controller
@RequestMapping("mealpick")
public class MealpickController {
	
	@Autowired
	private MealpickDAO mealpickDAO;
	
	
	@GetMapping("/getidmpc/{userpatient}")
	public String detail(Model model, @PathVariable("userpatient") String userpatient, Principal principal) {
		model.addAttribute("idmealpick",mealpickDAO.getMealpickByPatient(userpatient));
		String name = principal.getName();
	    model.addAttribute("username", name);
		return ("mealpick/getidmpc");
	}

}
