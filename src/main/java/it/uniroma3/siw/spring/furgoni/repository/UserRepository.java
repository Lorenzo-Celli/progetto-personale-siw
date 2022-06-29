package it.uniroma3.siw.spring.furgoni.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.furgoni.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByNomeAndCognome(String nome, String cognome);

}