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
	private CaloriesbibleDAO calbible;
	@Autowired
	private BranchDAO branchDAO;
	@Autowired
	private NutritionistDAO nutDAO;
	@Autowired
	private MealplanDAO mpDAO;
	@Autowired
	private PatientDAO patDAO;
	@Autowired
	private ChatDAO chatDAO;
	@Autowired
	private MembershipDAO memDAO;
	@Autowired
	private MealpickDAO mpcDAO;
	
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
	
//	@GetMapping("/changebranchkode")
//	public boolean changeBranchkode(
//			@RequestParam("id") Integer id,
//			@RequestParam("kode") String kdbranch) {
//
//		Branch branch = branchDAO.getBranchId(id);
//		branch.set(kdbranch);
//		return branchDAO.editBranch(branch);
//	}
	
	@GetMapping("/changebranchnama")
	public boolean changeBranchnama(
			@RequestParam("id") String id,
			@RequestParam("nama") String nama) {

		Branch branch = branchDAO.getBranchId(id);
		branch.setNamabranch(nama);
		return branchDAO.editBranch(branch);
	}
	
	@GetMapping("/changebranchalamat")
	public boolean changeBranchalamat(
			@RequestParam("id") String id,
			@RequestParam("alamat") String alamat) {

		Branch branch = branchDAO.getBranchId(id);
		branch.setAlamat(alamat);
		return branchDAO.editBranch(branch);
	}
	
	@GetMapping("/branchname")
	public List<String> getAllBranchName() {
		StringBuilder builder;
		List<String> hasil = new ArrayList<>();
		List<Branch> listBranch = entityManagerFactory.createEntityManager().createQuery("from Branch where isactive = 1").getResultList();
		
		for (Branch branch : listBranch) {
			builder = new StringBuilder();
			builder.append(branch.getNamabranch());
			hasil.add(builder.toString());
		}
		
		return hasil;
	}
	
	@GetMapping("/branchid")
	public List<String> getAllBranchId() {
		StringBuilder builder;
		List<String> hasil = new ArrayList<>();
		List<Branch> listBranch = entityManagerFactory.createEntityManager().createQuery("from Branch where isactive = 1").getResultList();
		
		for (Branch branch : listBranch) {
			builder = new StringBuilder();
			builder.append(branch.getIdbranch());
			hasil.add(builder.toString());
		}
		
		return hasil;
	}
	
	@GetMapping("/deleteBranch")
	public boolean deleteBranch(@RequestParam("idbr") String id) {
		Branch branch = branchDAO.getBranchId(id);
		branch.setIsactive(0);
		return branchDAO.editBranch(branch);
	}
	
	@GetMapping("/deleteNut")
	public boolean deleteNut(@RequestParam("idnut") String id) {
		Nutritionist nutritionist = nutDAO.getNutrionUser(id);
		nutritionist.setIsactive(0);
		return nutDAO.editNutritionist(nutritionist);
	}
	
	@GetMapping("/deletePat")
	public boolean deletePat(@RequestParam("idpat") String id) {
		Patient patient = patDAO.getPatientUser(id);
		patient.setIsactive(0);
		return patDAO.editPatient(patient);
	}

	
	@GetMapping("/patientname")
	public List<String> getAllPatient() {
		StringBuilder builder;
		List<String> hasil = new ArrayList<>();
		List<Patient> listPatient = entityManagerFactory.createEntityManager().createQuery("from Patient").getResultList();
		
		for (Patient patient : listPatient) {
			builder = new StringBuilder();
			builder.append(patient.getFullname());
			hasil.add(builder.toString());
		}
		
		return hasil;
	}
	
	@GetMapping("/patientid")
	public List<String> getAllPatientId() {
		StringBuilder builder;
		List<String> hasil = new ArrayList<>();
		List<Patient> listPatient = entityManagerFactory.createEntityManager().createQuery("from Patient").getResultList();
		
		for (Patient patient : listPatient) {
			builder = new StringBuilder();
			builder.append(patient.getUserpatient());
			hasil.add(builder.toString());
		}
		
		return hasil;
	}
	@GetMapping("/getallpat")
	public List<String> getAllUserPat(){
		List<String> patName = patDAO.getAllUserPatientName();
		return patName;
	}
	
	@GetMapping("/getallnut")
	public List<String> getAllUserNut(){
		List<String> nutName = nutDAO.getAllUserNut();
		return nutName;
	}
	
	@GetMapping("/getalluser")
	public List<String> getAllUser(){
		List<String> name = new ArrayList<>();
		List<String> patName = patDAO.getAllUserPatientName();
		List<String> nutName = nutDAO.getAllUserNut();
		name.addAll(nutName);
		name.addAll(patName);
		
		return name;
	}
	
	@GetMapping("/addchat")
	public boolean addChat(
			@RequestParam("user") String id,
			@RequestParam("mes") String mes) {

		Chat chat = new Chat();
		chat.setReceiverId(id);
		chat.setMessage(mes);
		chat.setSenderId("Admin");
		
		return chatDAO.addChat(chat);
	}
	
	@GetMapping("/delchat")
	public boolean delChat(@RequestParam("id") Integer id) {
		Chat chat = chatDAO.getChatId(id);
		return chatDAO.delChat(chat);
	}
	
	@GetMapping("/addmember")
	public boolean addMember(@RequestParam("user") String id) {
		
		Membership membership = new Membership();
		membership.setUserpatient(id);
		
		return memDAO.addMember(membership);
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
