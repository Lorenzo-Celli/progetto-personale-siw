package it.uniroma3.siw.spring.furgoni.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.furgoni.model.Furgone;
import it.uniroma3.siw.spring.furgoni.repository.FurgoneRepository;



@Service
public class FurgoneService {
	
	@Autowired
	private FurgoneRepository fr;

	@Transactional
	public void save(Furgone f) {
		fr.save(f);
	}
	
	public boolean exist(Furgone Furgone) {
		for (Furgone f : fr.findAll()) {
			if (f.equals(Furgone)) 
				return true;
		}
		return false;
	}
	
	public Furgone findById(Long id) {
		return fr.findById(id).get();
	}

	public List<Furgone> findAll(){
		List<Furgone> Furgones = new ArrayList<>();
		for (Furgone f : fr.findAll()) {
			Furgones.add(f);
		}
		return Furgones;
	}

	@Transactional
	public void delete(Furgone Furgone) {
		fr.delete(Furgone);
	}
	
}
