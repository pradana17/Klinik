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

import com.klinik.DAO.BranchDAO;
import com.klinik.model.Branch;

@Controller
@RequestMapping("/branch")
public class BranchController {

	@Autowired
	private BranchDAO branchDAO;
	
	@GetMapping("/index")
	public String formBranch(Model model) {
		Branch branch  = new Branch();
		model.addAttribute("semuabranch", branchDAO.getAllBranch());
		model.addAttribute("branch", branch);
		return "branch/index";
	}
	
	@PostMapping("/index")
	public String addBranch(@Valid Branch branch, BindingResult result) {
		
		if(!result.hasErrors() && branchDAO.addBranch(branch)) {
			return "redirect:/branch/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "branch/index";
		}
	}
}
