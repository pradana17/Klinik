package com.klinik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.klinik.DAO.ChatDAO;
import com.klinik.DAO.PatientDAO;
import com.klinik.model.Chat;


@Component
public class BackgroundService {
	
	@Autowired
	private ChatDAO chatDAO;
	@Autowired
	private PatientDAO patDAO;
	
	@Scheduled(cron="0 30 6 * * *")
	public boolean reminderBreakfast(){
		Chat cha;
		List<String> patName = patDAO.getAllUserPatientName();
		
		for (String eachname : patName) {
			cha = new Chat();
			cha.setSenderId("Admin");;
			cha.setReceiverId(eachname);
			cha.setMessage("Jangan Lupa sarapan sesuai menu yang disarankan");
			chatDAO.addChat(cha);
		}
		return true;
	}
	
	@Scheduled(cron="0 0 12 * * *")
	public boolean reminderLunch(){
		Chat cha;
		List<String> patName = patDAO.getAllUserPatientName();
		
		for (String eachname : patName) {
			cha = new Chat();
			cha.setSenderId("Admin");;
			cha.setReceiverId(eachname);
			cha.setMessage("Jangan Lupa makan siang sesuai menu yang disarankan");
			chatDAO.addChat(cha);
		}
		return true;
	}
	
	@Scheduled(cron="0 0 18 * * *")
	public boolean reminderDinner(){
		Chat cha;
		List<String> patName = patDAO.getAllUserPatientName();
		
		for (String eachname : patName) {
			cha = new Chat();
			cha.setSenderId("Admin");;
			cha.setReceiverId(eachname);
			cha.setMessage("Jangan Lupa makan malam sesuai menu yang disarankan");
			chatDAO.addChat(cha);
		}
		return true;
	}

}
