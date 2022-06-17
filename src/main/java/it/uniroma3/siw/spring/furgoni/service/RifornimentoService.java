package it.uniroma3.siw.spring.furgoni.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.furgoni.model.Rifornimento;
import it.uniroma3.siw.spring.furgoni.repository.RifornimentoRepository;



@Service
public class RifornimentoService {
	
	@Autowired
	private RifornimentoRepository rr;

	@Transactional
	public void save(Rifornimento r) {
		rr.save(r);
	}
	
	public boolean exist(Rifornimento Rifornimento) {
		for (Rifornimento r : rr.findAll()) {
			if (r.equals(Rifornimento)) 
				return true;
		}
		return false;
	}
	
	public Rifornimento findById(Long id) {
		return rr.findById(id).get();
	}

	public List<Rifornimento> findAll(){
		List<Rifornimento> Rifornimentos = new ArrayList<>();
		for (Rifornimento r : rr.findAll()) {
			Rifornimentos.add(r);
		}
		return Rifornimentos;
	}

	@Transactional
	public void delete(Rifornimento Rifornimento) {
		rr.delete(Rifornimento);
	}
	
}
