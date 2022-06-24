package it.uniroma3.siw.spring.furgoni.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.furgoni.model.Furgone;
import it.uniroma3.siw.spring.furgoni.model.Rifornimento;
import it.uniroma3.siw.spring.furgoni.model.Rotta;
import it.uniroma3.siw.spring.furgoni.model.User;
import it.uniroma3.siw.spring.furgoni.repository.FurgoneRepository;
import it.uniroma3.siw.spring.furgoni.repository.RifornimentoRepository;
import it.uniroma3.siw.spring.furgoni.repository.RottaRepository;
import it.uniroma3.siw.spring.furgoni.repository.UserRepository;
import it.uniroma3.siw.spring.furgoni.service.FurgoneService;
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
	
	
	//da mettere "admin" nel apth
	@GetMapping("/rotte")
	public String getRotte(Model model) {
		/*
		 * CREO UN PO DI ROTTE, FURGONI, RIFORNIMENTI,ECC. PER PROVA
		User d = this.uR.findById((long) 2).get();
		
		Rotta rA = new Rotta();
		rA.setData("25/09/2020");
		rA.setUser(d);
		
		d.getRotte().add(rA);
		
		
		Furgone f = new Furgone();
		f.setTarga("EF 789DZ");
		f.setKmAttuali(12300.78);
		f.getRotte().add(rA);
		
		rA.setFurgone(f);
		
		Rifornimento rif = new Rifornimento();
		rif.setData("25/09/2020");
		rif.setFurgone(f);
		rif.setImporto(40.00);
		rif.setRotta(rA);
		
		rA.setRifornimento(rif);

		rR.save(rA);
		uR.save(d);
		
		
		Rotta rConclusa = new Rotta();
		rConclusa.setUser(uR.findById((long)2).get());
		rConclusa.setData("30/24/2021");
		rConclusa.setFurgone(fR.findById((long)4).get());
		rConclusa.setKmIniziali(120.00);
		rConclusa.setKmFinali(125.00);
		
		Rifornimento rif2 = new Rifornimento();
		rif2.setData("25/09/2020");
		rif2.setFurgone(f);
		rif2.setImporto(40.00);
		rif2.setRotta(rConclusa);
		
		rConclusa.setRifornimento(rif2);
		
		rR.save(rConclusa);
		
		*/
		
		/*
		Furgone f = new Furgone();
		f.setTarga("EW 012GH");
		f.setKmAttuali(189220.45);
		
		Rifornimento rif = new Rifornimento();
		rif.setData("15/08/1995");
		rif.setFurgone(f);
		rif.setImporto(35.00);
	
		
		Rotta r = new Rotta();
		r.setData("15/08/1995");
		r.setFurgone(f);
		r.setKmFinali(170170.00);
		r.setKmIniziali(160160.00);
		r.setRifornimento(rif);
		r.setUser(this.uR.findById((long) 2).get());
		
		rif.setRotta(r);
		
		rR.save(r);
		*/
		
		
		List<Rotta> rotteCorrenti = new ArrayList<>();
		
		List<Rotta> rotteConcluse = new ArrayList<>();
		
		for (Rotta rtt : rottaService.findAll()) {
			if (rtt.getKmFinali() == null ) {
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
	@GetMapping("aggiungiRotta")
	public String aggiungiRotta (Model model) {
		
		model.addAttribute("drivers", this.userService.getAllUsers());
		
		model.addAttribute("furgoni", this.furgoneService.findAll());
		
		model.addAttribute("rotta", new Rotta());
		
		return "admin/aggiungiRottaForm";
	}
	
	@PostMapping("aggiungiRotta")
	public String aggiungiRottaPOST (@ModelAttribute("rotta") Rotta rotta) {

		User driver = this.userService.getUser(rotta.getUser().getId());
		
		Furgone furgone = this.furgoneService.findById(rotta.getFurgone().getId());
		
		Rotta rottaDaSalvare = new Rotta();
		
		rottaDaSalvare.setData(rotta.getData());
		rottaDaSalvare.setFurgone(furgone);
		rottaDaSalvare.setKmIniziali(furgone.getKmAttuali());
		rottaDaSalvare.setUser(driver);
		
		rottaService.save(rottaDaSalvare);
		
		return "redirect:/rotte";
	}
	
	
}
