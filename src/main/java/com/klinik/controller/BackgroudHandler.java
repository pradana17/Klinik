package com.klinik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.DAO.CaloriesbibleDAO;
import com.klinik.model.Caloriesbible;

@RestController
public class BackgroudHandler {

	@Autowired
	private CaloriesbibleDAO calbible;
	
	@GetMapping("/changeCalorie")
	public boolean changeCalorie(@RequestParam("idcal") short idcal, @RequestParam("calorie") Float calorie) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setCalorie(calorie);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/changeProtein")
	public boolean changeProtein(@RequestParam("idcal") short idcal, @RequestParam("protein") Float protein) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setProtein(protein);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/changeFats")
	public boolean changeFats(@RequestParam("idcal") short idcal, @RequestParam("fats") Float fats) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setFats(fats);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/changeCarb")
	public boolean changeCarb(@RequestParam("idcal") short idcal, @RequestParam("carbhdrt") Float carbhdrt) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setCarbhdrt(carbhdrt);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/changeCalcium")
	public boolean changeCalcium(@RequestParam("idcal") short idcal, @RequestParam("calcium") Float calcium) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setCalcium(calcium);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/changePhosphor")
	public boolean changePhosphor(@RequestParam("idcal") short idcal, @RequestParam("phosphor") Float phosphor) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setPhosphor(phosphor);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/changeIron")
	public boolean changeIron(@RequestParam("idcal") short idcal, @RequestParam("iron") Float iron) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setIron(iron);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/changeVita")
	public boolean changeVita(@RequestParam("idcal") short idcal, @RequestParam("vita") Float vita) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setVita(vita);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/changeVitb")
	public boolean changeVitb(@RequestParam("idcal") short idcal, @RequestParam("vitb1") Float vitb1) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setVitb1(vitb1);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/changeVitc")
	public boolean changeVitc(@RequestParam("idcal") short idcal, @RequestParam("vitc") Float vitc) {
		Caloriesbible updatedCalorie = calbible.getCaloriesbible(idcal);
		updatedCalorie.setVitc(vitc);
		return calbible.editCaloriesbible(updatedCalorie);
	}
	
	@GetMapping("/deleteCal")
	public boolean deleteCal(@RequestParam("idcal") short idcal) {
		Caloriesbible updateCalorie = calbible.getCaloriesbible(idcal);
		updateCalorie.setIsactive(0);
		return calbible.editCaloriesbible(updateCalorie);
	}
	
}
