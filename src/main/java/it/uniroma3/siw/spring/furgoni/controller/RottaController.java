package it.uniroma3.siw.spring.furgoni.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.furgoni.model.Credentials;
import it.uniroma3.siw.spring.furgoni.model.Furgone;
import it.uniroma3.siw.spring.furgoni.model.Rifornimento;
import it.uniroma3.siw.spring.furgoni.model.Rotta;
import it.uniroma3.siw.spring.furgoni.model.User;
import it.uniroma3.siw.spring.furgoni.repository.FurgoneRepository;
import it.uniroma3.siw.spring.furgoni.repository.RifornimentoRepository;
import it.uniroma3.siw.spring.furgoni.repository.RottaRepository;
import it.uniroma3.siw.spring.furgoni.repository.UserRepository;
import it.uniroma3.siw.spring.furgoni.service.CredentialsService;
import it.uniroma3.siw.spring.furgoni.service.FurgoneService;
import it.uniroma3.siw.spring.furgoni.service.RifornimentoService;
import it.uniroma3.siw.spring.furgoni.service.RottaService;
import it.uniroma3.siw.spring.furgoni.service.UserService;

@Controller
public class RottaController {
	
	@Autowired UserRepository uR;
	@Autowired RottaRepository rR;
	@Autowired FurgoneRepository fR;
	@Autowired RifornimentoRepository rifR;
	
	@Autowired
	RottaService rottaService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FurgoneService furgoneService;
	
	@Autowired
	RifornimentoService rifornimentoService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	
	//da mettere "admin" nel apth
	@GetMapping("/admin/rotte")
	public String getRotte(Model model) {
		
//		/* CREO UN PO DI ROTTE, FURGONI, RIFORNIMENTI,ECC. PER PROVA*/
//		User d = this.uR.findById((long) 2).get();
//		
//		Rotta rA = new Rotta();
//		rA.setData("25/09/2020");
//		rA.setUser(d);
//		
//		d.getRotte().add(rA);
//		
//		
//		Furgone f = new Furgone();
//		f.setTarga("EF 789DZ");
//		f.setKmAttuali(12300.78);
//		f.getRotte().add(rA);
//		
//		rA.setFurgone(f);
//		
//		Rifornimento rif = new Rifornimento();
//		rif.setData("25/09/2020");
//		rif.setFurgone(f);
//		rif.setImporto(40.00);
//		rif.setRotta(rA);
//		
//		rA.setRifornimento(rif);
//
//		rR.save(rA);
//		uR.save(d);
//		
//		
//		Rotta rConclusa = new Rotta();
//		rConclusa.setUser(uR.findById((long)2).get());
//		rConclusa.setData("30/24/2021");
//		rConclusa.setFurgone(fR.findById((long)4).get());
//		rConclusa.setKmIniziali(120.00);
//		rConclusa.setKmFinali(125.00);
//		
//		Rifornimento rif2 = new Rifornimento();
//		rif2.setData("25/09/2020");
//		rif2.setFurgone(f);
//		rif2.setImporto(40.00);
//		rif2.setRotta(rConclusa);
//		
//		rConclusa.setRifornimento(rif2);
//		
//		rR.save(rConclusa);
		
		
		
		
		List<Rotta> rotteCorrenti = new ArrayList<>();
		
		List<Rotta> rotteConcluse = new ArrayList<>();
		
		for (Rotta rtt : rottaService.findAll()) {
			if (rtt.getKmFinali() == 0 ) {
				rotteCorrenti.add(rtt);
			} else {
				rotteConcluse.add(rtt);
			}
		}
		
		model.addAttribute("rotteCorrenti", rotteCorrenti);
		
		model.addAttribute("rotteConcluse", rotteConcluse);
		
		
		return "admin/rotteAdmin";
	}

	//da mettere "admin" nel apth
	@RequestMapping(value="/admin/aggiungiRotta", method = RequestMethod.GET)
	public String aggiungiRotta (Model model) {
		
		model.addAttribute("drivers", this.userService.getAllUsers());
		
		model.addAttribute("furgoni", this.furgoneService.findAll());
		
		model.addAttribute("rotta", new Rotta());
		
		return "admin/aggiungiRottaForm.html";
	}
	
	@PostMapping("/admin/aggiungiRotta")
	public String aggiungiRottaPOST (@ModelAttribute("rotta") Rotta rotta) {

		User driver = this.userService.getUser(rotta.getUser().getId());
		
		Furgone furgone = this.furgoneService.findById(rotta.getFurgone().getId());
		
		Rotta rottaDaSalvare = new Rotta();
		
		if (rotta.getId() != null) rottaDaSalvare = rottaService.findById(rotta.getId());
		
		rottaDaSalvare.setData(rotta.getData());
		rottaDaSalvare.setFurgone(furgone);
		rottaDaSalvare.setKmIniziali(furgone.getKmAttuali());
		rottaDaSalvare.setUser(driver);
		
		rottaService.save(rottaDaSalvare);
		
		return "redirect:/admin/rotte";
	}
	
	@GetMapping("/admin/editRotta/{idR}")
	public String editRotta (@PathVariable("idR") Long idRotta, Model model) {
				
		model.addAttribute("rotta", rottaService.findById(idRotta));
		model.addAttribute("drivers", this.userService.getAllUsers());
		model.addAttribute("furgoni", this.furgoneService.findAll());
		
		return "admin/aggiungiRottaForm.html";
	}
	
	@GetMapping("/admin/deleteRotta/{idR}")
	public String deleteRotta (@PathVariable("idR") Long idRotta, Model model) {
				
		rottaService.delete(rottaService.findById(idRotta));
		
		return "redirect:/admin/rotte";
	}

	/*+++++++++++++++++++++++++++++++LATO USER++++++++++++++++++++++++++++++++++++++*/
	
	@GetMapping("/rotteConcluse")
	public String visualizzaRotteConcluse (Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		List<Rotta> rotte = rottaService.findAllByUserId(credentials.getUser().getId());
		
		List<Rotta> rotteConcluse = new ArrayList<>(); 
		
		for (Rotta rtt : rotte) {
			if (rtt.getKmFinali() != 0.0 )
				rotteConcluse.add(rtt);
				
		}
		
		
		model.addAttribute("rotte", rotteConcluse);
		return "rotteConcluse.html";
	}
	
	@GetMapping("/rottaDaCompilareForm")
	public String concludiRotta(Model model) {
		
		Rotta rottaDaCompilare = new Rotta();
		
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		List<Rotta> rotte = rottaService.findAllByUserId(credentials.getUser().getId());
		
		for (Rotta rtt : rotte) {
			if (rtt.getKmFinali() == 0.0 ) {
				rottaDaCompilare = rtt;
				break;
			}
			
		}		
		
		if (rottaDaCompilare.getFurgone() == null) {
			return "nessunaRotta.html";
		}
		
		model.addAttribute("rotta", rottaDaCompilare);
		return "rottaDaCompilareForm.html";
		
	}
	
	@PostMapping("/rottaDaCompilareForm")
	public String concludiRottaPost(@ModelAttribute("rotta") Rotta rotta) {
				
		double kmFinali = rotta.getKmFinali();
		
		Rotta rottaDaSalvare = rottaService.findById(rotta.getId());
		
		if (rotta.getRifornimento().getImporto() != null) {
			
			Rifornimento rifornimentoDaSalvare = rotta.getRifornimento();
			
			rifornimentoDaSalvare.setRotta(rottaDaSalvare);
			rifornimentoDaSalvare.setFurgone(rottaDaSalvare.getFurgone());
			rottaDaSalvare.setRifornimento(rifornimentoDaSalvare);
			
			rifornimentoService.save(rifornimentoDaSalvare);
			
			
		}
		
		rottaDaSalvare.setKmFinali(kmFinali);
		rottaDaSalvare.getFurgone().setKmAttuali(kmFinali);
		
		
		
		rottaService.save(rottaDaSalvare);
		
		return "redirect:/default";
		
	}
	
	
}
