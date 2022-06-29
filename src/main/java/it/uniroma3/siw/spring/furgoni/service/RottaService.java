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
	private RottaRepository rottaRepository;

	@Transactional
	public void save(Rotta r) {
		rottaRepository.save(r);
	}

	public boolean exist(Rotta Rotta) {
		for (Rotta r : rottaRepository.findAll()) {
			if (r.equals(Rotta)) 
				return true;
		}
		return false;
	}

	public Rotta findById(Long id) {
		return rottaRepository.findById(id).get();
	}

	public List<Rotta> findAll(){
		List<Rotta> Rottas = new ArrayList<>();
		for (Rotta r : rottaRepository.findAll()) {
			Rottas.add(r);
		}
		return Rottas;
	}

	@Transactional
	public void delete(Rotta Rotta) {
		rottaRepository.delete(Rotta);
	}

	public List<Rotta> findAllByUserId(Long userId){
		return rottaRepository.findByUserId(userId);
	}

	public Rotta findByDataAndUserId(String data ,Long userId){
		return rottaRepository.findByDataAndUserId(data, userId);
	}
	
	public Rotta findByDataAndFurgoneId(String data ,Long furgoneId){
		return rottaRepository.findByDataAndFurgoneId(data, furgoneId);
	}

	/*ef c = chefRepository.findByNomeAndCognome(chef.getNome(), chef.getCognome());

		if (c != null)
			return true;
		else 
			return false;*/

	public boolean alreadyExists(Rotta rotta) {
		Rotta rottaDaControllare =
				rottaRepository.findByDataAndUserId(rotta.getData(), rotta.getUser().getId());
		if(rottaDaControllare!=null)
			return true;
		else 
			return false;

	}

	public boolean alreadyTake(Rotta rotta) {
		Rotta rottaDaControllare =rottaRepository.findByDataAndFurgoneId(rotta.getData(), rotta.getFurgone().getId());

		if(rotta.furgoneGiaPreso(rottaDaControllare))
			return true;
		return false;

	}





}
