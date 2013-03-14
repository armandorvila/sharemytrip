package com.armandorv.sharemytrip.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

	@RequestMapping({ "/contact" })
	public String setUpContactForm() {
		return "contact";
	}

	@RequestMapping({ "/sendMessage" })
	public String sendMessage() {
		return "home";
	}
}
