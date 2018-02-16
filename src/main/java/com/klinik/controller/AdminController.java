package com.klinik.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.klinik.DAO.BranchDAO;
import com.klinik.DAO.MealplanDAO;
import com.klinik.DAO.NutritionistDAO;
import com.klinik.DAO.PatientDAO;
import com.klinik.model.Branch;
import com.klinik.model.Mealplan;
import com.klinik.model.Nutritionist;
import com.klinik.model.Patient;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private BranchDAO branchDAO;
	private NutritionistDAO nutritionistDAO;
	private PatientDAO patientDAO;
	private MealplanDAO mealplanDAO;
	private static String UPLOADED_FOLDER = "E://";
	
	@GetMapping("/managebranch")
	public String formBranch(Model model) {
		Branch branch  = new Branch();
		model.addAttribute("semuabranch", branchDAO.getAllBranch());
		model.addAttribute("branch", branch);
		return "admin/managebranch";
	}
	
	@PostMapping("/managebranch")
	public String addBranch(@Valid Branch branch, BindingResult result) {
		
		if(!result.hasErrors() && branchDAO.addBranch(branch)) {
			return "redirect:/admin/managebranch";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "admin/managebranch";
		}
	}
	
	@GetMapping("/managenut")
	public String formNut(Model model) {
		Nutritionist nutritionist  = new Nutritionist();
		model.addAttribute("semuaNut", nutritionistDAO.getAllNutritionist());
		model.addAttribute("nut", nutritionist);
		return "admin/managenut";
	}
	
	@PostMapping("/managenut")
	public String addNut(@Valid Nutritionist nutritionist, BindingResult result) {
		
		if(!result.hasErrors() && nutritionistDAO.addNutritionist(nutritionist)) {
			return "redirect:/admin/managenut";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "admin/managenut";
		}
	}
	
	@GetMapping("/managepat")
	public String formPat(Model model) {
		Patient patient  = new Patient();
		model.addAttribute("semuaPat", patientDAO.getAllPatient());
		model.addAttribute("pat", patient);
		return "admin/managepatient";
	}
	
	@PostMapping("/managepat")
	public String addPatient(@Valid Patient patient, BindingResult result) {
		
		if(!result.hasErrors() && patientDAO.addPatient(patient)) {
			return "redirect:/admin/managepatient";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "admin/managepatient";
		}
	}
	
	@GetMapping("/managemealplan")
	public String indexMeal(Model model) {
		Mealplan mealplan = new Mealplan();
		model.addAttribute("semuaMeal", mealplanDAO.getAllMeal());
		model.addAttribute("mp", mealplan);
		return "admin/managemealplan";
	}
	
    @PostMapping("/managemealplan")
    public String singleFileUpload(@Valid Mealplan mealplan ,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            byte[] bytes = file.getBytes();
            String filenames = file.getOriginalFilename();
            mealplan.setFiles(bytes);
            mealplan.setFilename(filenames);
            mealplanDAO.addMeal(mealplan);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "admin/managemealplan";
    }
}
