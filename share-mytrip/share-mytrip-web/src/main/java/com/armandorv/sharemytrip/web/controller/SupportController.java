package com.armandorv.sharemytrip.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SupportController {

	@RequestMapping("support")
	public String showSupport() {
		return "support";
	}
}
