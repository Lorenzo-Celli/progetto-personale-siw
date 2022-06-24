package it.uniroma3.siw.spring.furgoni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.spring.furgoni.model.Furgone;
import it.uniroma3.siw.spring.furgoni.service.FurgoneService;

@Controller
public class FurgoneController {
	
	@Autowired
	private FurgoneService furgoneService;
	
	
	@GetMapping("/dettaglioFurgone/{idFurgone}")
	public String dettaglioFurgone (@PathVariable Long idFurgone, Model model) {
		
		Furgone furgone = this.furgoneService.findById(idFurgone);
		
		model.addAttribute("furgone", furgone);
		
		model.addAttribute("rotteFurgone", furgone.getRotte());
		
		model.addAttribute("rifornimentiFurgone", furgone.getRifornimenti());
		
		
		return "admin/dettagliFurgone";
	}

}
