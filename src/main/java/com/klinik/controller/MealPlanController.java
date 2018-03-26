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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.klinik.DAO.MealpickDAO;
import com.klinik.DAO.MealplanDAO;
import com.klinik.model.Mealplan;

@Controller
@RequestMapping("mealplan")
public class MealPlanController {
	
	@Autowired
	private MealplanDAO mpDAO;
	
	@GetMapping("/index")
	public String indexMeal(Model model) {
		Mealplan mealplan = new Mealplan();
		model.addAttribute("semuaMeal", mpDAO.getAllMeal());
		model.addAttribute("mp", mealplan);
	
		return "mealplan/index";
	}
	
    @PostMapping("/index")
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

        return "mealplan/index";
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
        return "mealplan/index :: viewmp";
    }
    
}
