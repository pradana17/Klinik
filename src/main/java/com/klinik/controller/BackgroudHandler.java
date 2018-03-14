package com.klinik.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.DAO.BranchDAO;
import com.klinik.DAO.CaloriesbibleDAO;
import com.klinik.DAO.ChatDAO;
import com.klinik.DAO.MealpickDAO;
import com.klinik.DAO.MealplanDAO;
import com.klinik.DAO.MembershipDAO;
import com.klinik.DAO.NutritionistDAO;
import com.klinik.DAO.PatientDAO;
import com.klinik.model.Branch;
import com.klinik.model.Caloriesbible;
import com.klinik.model.Chat;
import com.klinik.model.Mealpick;
import com.klinik.model.Mealplan;
import com.klinik.model.Membership;
import com.klinik.model.Nutritionist;
import com.klinik.model.Patient;



@RestController
public class BackgroudHandler {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private MealplanDAO mpDAO;
	
	
	@Autowired
	private MembershipDAO memDAO;
	@Autowired
	private MealpickDAO mpcDAO;
	
	
	@GetMapping("/api/addmember")
	public boolean addMember(@RequestParam("user") String id) {
		
		Membership membership = new Membership();
		membership.setUserpatient(id);
		
		return memDAO.addMember(membership);
	}
	
	
	@GetMapping("/getidmp")
	public List<String> getIdMealPlan(@RequestParam("userpatient") String id){
		StringBuilder builder;
		List<String> idmp = new ArrayList<>();
		List<Mealpick> list = mpcDAO.getMealpickByPatient(id);
		
		for (Mealpick mealpick : list) {
			builder = new StringBuilder();
			builder.append(mealpick.getIdmealplan().getIdmealplan());
			idmp.add(builder.toString());
		}
		return idmp;
	}
	
	@GetMapping("/download")
	public String download(@RequestParam("idmealplan") Integer idmealplan, HttpServletResponse response) {

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
}

