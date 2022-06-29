package it.uniroma3.siw.spring.furgoni.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.spring.furgoni.model.User;
import it.uniroma3.siw.spring.furgoni.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/admin/deleteDriver/{idR}")
	public String deleteUser (@PathVariable("idR") Long id, Model model) {

		userService.delete(userService.findById(id));

		return "redirect:/admin/driver";
	}
	
	@GetMapping("/admin/driver")
	public String getDriver(Model model) {
		
		List<User> driver = new ArrayList<>();
		for(User user : this.userService.findAllDriver())
			driver.add(user);
		
		model.addAttribute("drivers" , driver);
		return "/admin/driver";
		
	}

}
