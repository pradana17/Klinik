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
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private BranchDAO branchDAO;
	@Autowired
	private NutritionistDAO nutDAO;
	@Autowired
	private PatientDAO patDAO;
	@Autowired
	private MealplanDAO mpDAO;
	@Autowired
	private ChatDAO chatDAO;
	
	@GetMapping("/managebranch")
	public String formBranch(Model model, Principal principal) {
		Branch branch  = new Branch();
		model.addAttribute("semuabranch", branchDAO.getAllBranch());
		model.addAttribute("branch", branch);
		String name = principal.getName();
	    model.addAttribute("username", name);
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
	public String formNut(Model model, Principal principal) {
		Nutritionist nutritionist  = new Nutritionist();
		model.addAttribute("semuaNut", nutDAO.getAllNutritionist());
		model.addAttribute("nut", nutritionist);
		String name = principal.getName();
	    model.addAttribute("username", name);
		return "admin/managenut";
	}
	
	@PostMapping("/managenut")
	public String addNut(@Valid Nutritionist nutritionist, BindingResult result) {
		
		if(!result.hasErrors() && nutDAO.addNutritionist(nutritionist)) {
			return "redirect:/admin/managenut";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "admin/managenut";
		}
	}
	
	@GetMapping("/managepat")
	public String formPat(Model model, Principal principal) {
		Patient patient  = new Patient();
		model.addAttribute("semuaPat", patDAO.getAllPatient());
		model.addAttribute("pat", patient);
		String name = principal.getName();
	    model.addAttribute("username", name);
		return "admin/managepatient";
	}
	
	@PostMapping("/managepat")
	public String addPatient(@Valid Patient patient, BindingResult result) {
		
		if(!result.hasErrors() && patDAO.addPatient(patient)) {
			return "redirect:/admin/managepat";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "admin/managepatient";
		}
	}
	
//	@GetMapping("/managepat")
//	public String formPat(Model model) {
//		Membership membership = new Membership();
//		model.addAttribute("semuaMem", memDAO.getAllMember());
//		model.addAttribute("mem", membership);
//		return "admin/managepatient";
//	}
//	
//	@PostMapping("/managepat")
//	public String addPatient(@Valid Membership membership, BindingResult result) {
//		
//		if(!result.hasErrors() && memDAO.addMember(membership)) {
//			return "redirect:/admin/managepatient";
//		} else {
//			for (ObjectError er : result.getAllErrors()) {
//				System.out.println(er.getDefaultMessage());
//			}
//			return "admin/managepatient";
//		}
//	}
	
	@GetMapping("/managemealplan")
	public String indexMeal(Model model, Principal principal) {
		Mealplan mealplan = new Mealplan();
		model.addAttribute("semuaMeal", mpDAO.getAllMeal());
		model.addAttribute("mp", mealplan);
		String name = principal.getName();
	    model.addAttribute("username", name);
		return "admin/managemealplan";
	}
	
    @PostMapping("/managemealplan")
    public String singleFileUpload(@Valid Mealplan mealplan ,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            byte[] bytes = file.getBytes();
            String filenames = file.getOriginalFilename();
            String filetype = file.getContentType();
            mealplan.setFiles(bytes);
            mealplan.setFilename(filenames);
            mealplan.setTypefile(filetype);
            mpDAO.addMeal(mealplan);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "admin/managemealplan";
    }
    
    @GetMapping("/download/{idmealplan}")
	public String download(@PathVariable("idmealplan")
	Integer idmealplan, HttpServletResponse response) {

		Mealplan mealplan = mpDAO.getMealId(idmealplan);
		try {
			response.setHeader("Content-Disposition", "inline;filename=\"" +mealplan.getFilename()+ "\"");
			OutputStream out = response.getOutputStream();
			ByteArrayInputStream inputStream = new ByteArrayInputStream(mealplan.getFiles());
			response.setContentType(mealplan.getTypefile());
			IOUtils.copy(inputStream, out);
			out.flush();
			out.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    
    @GetMapping("/detailmp/{idmealplan}")
    public String viewMP(Model model, @PathVariable("idmealplan") Integer id) {
        Mealplan mealplan = mpDAO.getMealId(id);
        model.addAttribute("objMP", mealplan);
        return "admin/managemealplan :: viewmp";
    }
    
    @GetMapping("/detailnut/{usernutritionist}")
    public String viewNut(@PathVariable("usernutritionist") String user, ModelMap modelMap) {
        Nutritionist nutritionist = nutDAO.getNutrionUser(user);
        modelMap.addAttribute("objNut", nutritionist);
        return "admin/managenut :: viewnut";
    }
    
    @GetMapping("/detailpat/{userpatient}")
    public String viewPat(@PathVariable("userpatient") String user, ModelMap modelMap) {
        Patient patient = patDAO.getPatientUser(user);
        modelMap.addAttribute("objPatient", patient);
        return "admin/managepat :: viewpat";
    }
    
    @GetMapping("/managechat")
	public String formChat(Model model) {
		model.addAttribute("semuaChat", chatDAO.getAllChat());
		return "admin/managechat";
	}
	
}
