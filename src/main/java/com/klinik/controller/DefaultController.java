package com.klinik.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/admin/managebranch";
        }
        else if (request.isUserInRole("NUT")) {
        	return "redirect:/caloriesbible/index";
        }
        return "redirect:/patient/caloriesbible";
    }

}

