package com.klinik.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.DAO.NutritionistDAO;
import com.klinik.DAO.PatientDAO;

@RestController
@RequestMapping("/api/user")
public class UserAPI {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private NutritionistDAO nutDAO;
	
	@Autowired
	private PatientDAO patDAO;

	
	@GetMapping("/getalluser")
	public List<String> getAllUser(){
		List<String> name = new ArrayList<>();
		List<String> patName = patDAO.getAllUserPatientName();
		List<String> nutName = nutDAO.getAllUserNut();
		name.addAll(nutName);
		name.addAll(patName);
		
		return name;
	}
	

}
