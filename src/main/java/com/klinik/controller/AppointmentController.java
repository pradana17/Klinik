package com.klinik.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klinik.DAO.AppointmentDAO;
import com.klinik.model.Appointment;

@Controller
@RequestMapping("appointment")
public class AppointmentController {
	@Autowired
	AppointmentDAO appointmentDAO = new AppointmentDAO();

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("getAppointment", appointmentDAO.getAllAppointments());
		Appointment appointment = new Appointment();
		model.addAttribute("janji", appointment);
		return "appointment/index";
	}

	@PostMapping("/index")
	public String addAppointment(@Valid Appointment appointment, BindingResult result) {
		System.out.println(result.hasErrors() +" -disini- "+ appointmentDAO.addAppointment(appointment));
		if (!result.hasErrors() && appointmentDAO.addAppointment(appointment)) {
			return "redirect:/appointment/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "appointment/index";
		}

	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("actorId") int Id) {
		model.addAttribute("getID", appointmentDAO.getAppointmentID(Id));
		model.addAttribute("getAppointment", appointmentDAO.getAllAppointments());
		Appointment appointment = new Appointment();
		model.addAttribute("janji", appointment);
		return "appointment/edit";
	}
	
	@GetMapping("/validation")
	public String updateApproved(Model model) {
		model.addAttribute("getAppointment", appointmentDAO.getAllAppointments());
		Appointment appointment = new Appointment();
		model.addAttribute("janji", appointment);
		return "appointment/validation"; 
	}

	@PostMapping("/edit")
	public String updateApproved(@Valid Appointment appointment, BindingResult result) {
		System.out.println(result.hasErrors() +" haha "+ appointmentDAO.ValidateAppointment(appointment));
		if (!result.hasErrors() && appointmentDAO.ValidateAppointment(appointment)) {
			System.out.println("kuy");
			return "redirect:/appointment/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "appointment/edit";
		}
	}
}
