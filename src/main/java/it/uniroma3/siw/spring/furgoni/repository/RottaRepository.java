package it.uniroma3.siw.spring.furgoni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.furgoni.model.Rotta;

public interface RottaRepository extends CrudRepository<Rotta, Long> {
	
	public List<Rotta> findByUserId(Long userId);

}