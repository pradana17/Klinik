package com.klinik.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.klinik.DAO.BranchDAO;
import com.klinik.DAO.ChatDAO;
import com.klinik.DAO.MealplanDAO;
import com.klinik.DAO.NutritionistDAO;
import com.klinik.DAO.PatientDAO;
import com.klinik.model.Branch;
import com.klinik.model.Mealplan;
import com.klinik.model.Nutritionist;
import com.klinik.model.Patient;

@Controller
@RequestMapping("nutritionist")
public class NutritionistController {
		
	@Autowired
	private NutritionistDAO nutDAO;
	
	
	
	@GetMapping("/index")
	public String formNut(Model model) {
		Nutritionist nutritionist  = new Nutritionist();
		model.addAttribute("semuaNut", nutDAO.getAllNutritionist());
		model.addAttribute("nut", nutritionist);
		
		return "nutritionist/index";
	}
	
	@PostMapping("/index")
	public String addNut(@Valid Nutritionist nutritionist, BindingResult result) {
		
		if(!result.hasErrors() && nutDAO.addNutritionist(nutritionist)) {
			return "redirect:/nutritionist/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "nutritionist/index";
		}
	}
	
	

    
    
    @GetMapping("/detailnut/{usernutritionist}")
    public String viewNut(@PathVariable("usernutritionist") String user, ModelMap modelMap) {
        Nutritionist nutritionist = nutDAO.getNutrionUser(user);
        modelMap.addAttribute("objNut", nutritionist);
        return "admin/managenut :: viewnut";
    }
    
	
}
