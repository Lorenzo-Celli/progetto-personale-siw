package it.uniroma3.siw.spring.furgoni.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.furgoni.model.Rotta;
import it.uniroma3.siw.spring.furgoni.repository.RottaRepository;



@Service
public class RottaService {
	
	@Autowired
	private RottaRepository rr;

	@Transactional
	public void save(Rotta r) {
		rr.save(r);
	}
	
	public boolean exist(Rotta Rotta) {
		for (Rotta r : rr.findAll()) {
			if (r.equals(Rotta)) 
				return true;
		}
		return false;
	}
	
	public Rotta findById(Long id) {
		return rr.findById(id).get();
	}

	public List<Rotta> findAll(){
		List<Rotta> Rottas = new ArrayList<>();
		for (Rotta r : rr.findAll()) {
			Rottas.add(r);
		}
		return Rottas;
	}

	@Transactional
	public void delete(Rotta Rotta) {
		rr.delete(Rotta);
	}
	
}
