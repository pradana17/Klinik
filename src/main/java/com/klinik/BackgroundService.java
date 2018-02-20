package com.klinik;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.klinik.DAO.ChatDAO;
import com.klinik.DAO.PatientDAO;
import com.klinik.model.Chat;
import com.klinik.model.Patient;


@Component
public class BackgroundService {
	
	@Autowired
	private ChatDAO chatDAO;
	@Autowired
	private PatientDAO patDAO;
	
	@Scheduled(cron="0 30 6 * * *")
	public boolean reminderBreakfast(){
		Chat cha;
		StringBuilder builder;
		List<String> patName = new ArrayList<>();
		List<Patient> allPatient = patDAO.getAllPatient();
		
		for (Patient patient : allPatient) {
			builder = new StringBuilder();
			builder.append(patient.getUserpatient());
			patName.add(builder.toString());
		}
		
		for (String eachname : patName) {
			cha = new Chat();
			cha.setSenderId("Admin");;
			cha.setReceiverId(eachname);
			cha.setDatesending(new Date());
			cha.setDatereceiver(new Date());
			cha.setMessage("Jangan Lupa sarapan sesuai menu yang disarankan");
			chatDAO.addChat(cha);
		}
		return true;
	}
	
	@Scheduled(cron="0 0 12 * * *")
	public boolean reminderLunch(){
		Chat cha;
		StringBuilder builder;
		List<String> patName = new ArrayList<>();
		List<Patient> allPatient = patDAO.getAllPatient();
		
		for (Patient patient : allPatient) {
			builder = new StringBuilder();
			builder.append(patient.getUserpatient());
			patName.add(builder.toString());
		}
		
		for (String eachname : patName) {
			cha = new Chat();
			cha.setSenderId("Admin");;
			cha.setReceiverId(eachname);
			cha.setDatesending(new Date());
			cha.setDatereceiver(new Date());
			cha.setMessage("Jangan Lupa makan siang sesuai menu yang disarankan");
			chatDAO.addChat(cha);
		}
		return true;
	}
	
	@Scheduled(cron="0 0 18 * * *")
	public boolean reminderDinner(){
		Chat cha;
		StringBuilder builder;
		List<String> patName = new ArrayList<>();
		List<Patient> allPatient = patDAO.getAllPatient();
		
		for (Patient patient : allPatient) {
			builder = new StringBuilder();
			builder.append(patient.getUserpatient());
			patName.add(builder.toString());
		}
		
		for (String eachname : patName) {
			cha = new Chat();
			cha.setSenderId("Admin");;
			cha.setReceiverId(eachname);
			cha.setDatesending(new Date());
			cha.setDatereceiver(new Date());
			cha.setMessage("Jangan Lupa makan malam sesuai menu yang disarankan");
			chatDAO.addChat(cha);
		}
		return true;
	}

}
