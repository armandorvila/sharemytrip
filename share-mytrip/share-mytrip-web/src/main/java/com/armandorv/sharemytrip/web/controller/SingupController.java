package com.armandorv.sharemytrip.web.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.armandorv.sharemytrip.business.model.Credentials;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.service.TravelersService;

@Controller
public class SingupController {

	private static Logger log = Logger.getLogger(SingupController.class);

	@Autowired
	private TravelersService travelersService;

	@RequestMapping({ "/singup" })
	public String singup(Model model) {
		model.addAttribute("traveler", new Traveler());
		return "singup";
	}

	@RequestMapping(value= "/newTraveler" , method=RequestMethod.POST)
	public String newTraveler(
			@RequestParam(required = false, value = "username") String username,
			@RequestParam(required = false, value = "password") String password,
			@RequestParam(required = false, value = "passwordConfirmation") String passwordConfirmation,
			@Valid @ModelAttribute("traveler") Traveler traveler,Model model,BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			log.info("Validation error " + bindingResult.getFieldErrors());
			return "singup";
		}

		if (!validateCredentials(username, password, passwordConfirmation,
				bindingResult)) {
			return "singup";
		}

		travelersService.newTraveler(traveler, new Credentials(username,
				password));

		return "redirect:/home";
	}

	private boolean validateCredentials(String username, String password,
			String passwordConfirmation, BindingResult bindingResult) {
		if ("".equals(username) || "".equals(password) || "".equals(passwordConfirmation)){
			//bindingResult.addError(new ObjectError("username", "El usuario y la contraseña no deben ser null"));
			return false;
		}
		
		if(password.length()<5){
			return false;
		}
		
		if(password.matches("[1-9]$")){
			return false;
		}
		
		if(!password.equals(passwordConfirmation)){
			return false;
		}
		
		return true;
	}
}
