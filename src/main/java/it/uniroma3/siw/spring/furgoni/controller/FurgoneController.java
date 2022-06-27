package it.uniroma3.siw.spring.furgoni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.furgoni.model.Furgone;
import it.uniroma3.siw.spring.furgoni.service.FurgoneService;

@Controller
public class FurgoneController {
	
	@Autowired
	private FurgoneService furgoneService;
	
	
	@GetMapping("admin/dettaglioFurgone/{idFurgone}")
	public String dettaglioFurgone (@PathVariable Long idFurgone, Model model) {
		
		Furgone furgone = this.furgoneService.findById(idFurgone);
		
		model.addAttribute("furgone", furgone);
		
		model.addAttribute("rotteFurgone", furgone.getRotte());
		
		model.addAttribute("rifornimentiFurgone", furgone.getRifornimenti());
		
		
		return "admin/dettagliFurgone";
	}
	
	@GetMapping("/admin/furgoneForm")
	public String aggiungiFurgone (Model model) {
		model.addAttribute("furgone", new Furgone());
		return "/admin/furgoneForm";
	}
	
	@PostMapping("/admin/furgoneForm")
	public String aggiungiFurgonePOST (@ModelAttribute("furgone") Furgone furgone, Model model) {
		
		if (furgone.getId() != null) {
		Furgone furgoneAttuale = furgoneService.findById(furgone.getId());
		
		furgoneAttuale.setKmAttuali(furgone.getKmAttuali());
		furgoneAttuale.setTarga(furgone.getTarga());
		
		furgoneService.save(furgoneAttuale);
		
		return "redirect:/default";
		}
		
		furgoneService.save(furgone);
		
		return "redirect:/default";
	}
	
	@GetMapping("/admin/furgoni")
	public String visualizzaFurgoni(Model model) {
		
		model.addAttribute("furgoni", furgoneService.findAll());
		
		return "/admin/furgoni.html";
	}
	
	
	@GetMapping("/admin/editFurgone/{idF}")
	public String editFurogne(@PathVariable("idF") Long id, Model model) {
		
		model.addAttribute("furgone", furgoneService.findById(id));
		
		return "/admin/furgoneForm.html";
	}
	
	@GetMapping("/admin/deleteFurgone/{idF}")
	public String deleteFurogne(@PathVariable("idF") Long id, Model model) {
		
		furgoneService.delete(furgoneService.findById(id));
		
		model.addAttribute("furgoni", furgoneService.findAll());
		
		return "/admin/furgoni.html";
	}

}
