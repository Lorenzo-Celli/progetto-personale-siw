package it.uniroma3.siw.spring.furgoni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.spring.furgoni.service.RifornimentoService;

@Controller
public class RifornimentoController {
	
	@Autowired
	RifornimentoService rifornimentoService;
	
	@GetMapping("/admin/rifornimenti")
	public String visualizzaRifornimenti(Model model) {
		
		model.addAttribute("rifornimenti", rifornimentoService.findAll());
		
		return "/admin/rifornimenti.html";
	}
	

}
