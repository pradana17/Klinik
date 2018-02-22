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
		System.out.println(result.hasErrors() +" -- "+ appointmentDAO.addAppointment(appointment));
		if (!result.hasErrors() && appointmentDAO.addAppointment(appointment)) {
			return "redirect:/appointment/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "appointment/index";
		}

	}

	@GetMapping("/validation/{IdAppointment}")
	public String updateApproved(Model model, @PathVariable("IdAppointment") int Id) {
		model.addAttribute("validate", appointmentDAO.getAppointmentID(Id));
		return "appointment/validation";
	}

	@PostMapping("/validation")
	public String updateApproved(@Valid Appointment appointment, BindingResult result) {
		if (!result.hasErrors() && appointmentDAO.ValidateAppointment(appointment)) {
			return "redirect:/appointment/index";
		} else {
			for (ObjectError er : result.getAllErrors()) {
				System.out.println(er.getDefaultMessage());
			}
			return "appointment/validation/";
		}
	}
}
