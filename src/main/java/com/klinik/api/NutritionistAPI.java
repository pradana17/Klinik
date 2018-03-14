package com.klinik.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.DAO.NutritionistDAO;
import com.klinik.model.Nutritionist;

@RestController
@RequestMapping("/api/user/")

public class NutritionistAPI {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private NutritionistDAO nutDAO;
	
	@GetMapping("/getallnut")
	public List<String> getAllUserNut(){
		List<String> nutName = nutDAO.getAllUserNut();
		return nutName;
	}
	
	@GetMapping("/nutuser")
	public List<String> getAllNutNamebyBranch(@RequestParam("branch") String id) {
		StringBuilder builder;
		List<String> nutName = new ArrayList<>();
		List<Nutritionist> listNut = nutDAO.getNutrionUserbyBranch(id);
		
		for (Nutritionist nut : listNut) {
			builder = new StringBuilder();
			builder.append(nut.getUsernutritionist());
			nutName.add(builder.toString());
		}
		
		return nutName;
	}


	
	@GetMapping("/deleteNut")
	public boolean deleteNut(@RequestParam("idnut") String id) {
		Nutritionist nutritionist = nutDAO.getNutrionUser(id);
		nutritionist.setIsactive(0);
		return nutDAO.editNutritionist(nutritionist);
	}
}
