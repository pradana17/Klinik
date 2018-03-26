package com.klinik.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/branch/index";
        }
        if (request.isUserInRole("NUT")) {
        	return "redirect:/caloriesbible/index";
        }
        if (request.isUserInRole("PAT")) {
        	return "redirect:/patient/caloriesbible";
        }
        
        return "/login";
    }

}

