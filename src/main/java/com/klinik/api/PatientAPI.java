package com.klinik.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.DAO.PatientDAO;
import com.klinik.model.Patient;

@RestController
@RequestMapping("/api/user/")
public class PatientAPI {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private PatientDAO patDAO;


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
	
	
}
