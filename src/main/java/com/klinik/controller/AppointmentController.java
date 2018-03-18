package com.klinik.controller;

import java.security.Principal;

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
import com.klinik.model.Mealplan;

@Controller
@RequestMapping("appointment")
public class AppointmentController {
	@Autowired
	AppointmentDAO appointmentDAO;

	@GetMapping("/index")
	public String index(Model model, Principal principal) {
		Appointment appointment = new Appointment();
		model.addAttribute("getAppointment", appointmentDAO.getAllAppointments());
		model.addAttribute("janji", appointment);
		String name = principal.getName();
		model.addAttribute("username", name);
		return "appointment/index";
	}

	@PostMapping("/index")
	public String addAppointment(@Valid Appointment appointment, BindingResult result) {
		System.out.println(result.hasErrors() +" -disini- "+ appointmentDAO.addAppointment(appointment));
		if (!result.hasErrors() && appointmentDAO.addAppointment(appointment)) {
			return "redirect:/appointment/appPatient";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "appointment/index";
		}
	}
	
	@GetMapping("/appNutritionist")
	public String appNut(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("appNut", appointmentDAO.getAppointmentNut(name));		
		return "appointment/appNutritionist";
	}
	
	@GetMapping("/appPatient")
	public String appPatient(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("appPat", appointmentDAO.getAppointmentPat(name));		
		return "appointment/appPatient";
	}
	
	@PostMapping("/validation")
	public String updateApproved(@Valid Appointment appointment, BindingResult result) {
		if (!result.hasErrors() && appointmentDAO.ValidateAppointment(appointment)) {
			return "redirect:/appointment/validation";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "appointment/validation";
		}
	}
	
	@GetMapping("/validation")
	public String updateApproved(Model model) {
		model.addAttribute("getAppointment", appointmentDAO.getAllAppointments());
		Appointment appointment = new Appointment();
		model.addAttribute("janji", appointment);
		return "appointment/validation"; 
	}
}
