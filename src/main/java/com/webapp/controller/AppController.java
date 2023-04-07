package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController extends BaseController {

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		return "redirect:/master/users/";
	}
}
