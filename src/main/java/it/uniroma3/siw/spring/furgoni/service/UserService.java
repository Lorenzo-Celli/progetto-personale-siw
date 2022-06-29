package it.uniroma3.siw.spring.furgoni.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.furgoni.model.User;
import it.uniroma3.siw.spring.furgoni.model.Credentials;
import it.uniroma3.siw.spring.furgoni.repository.CredentialsRepository;
import it.uniroma3.siw.spring.furgoni.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;
    
    @Autowired
    protected CredentialsRepository credentialsRepository;


    @Transactional
    public User getUser(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }


    @Transactional
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }
    
    public User findById(Long id) {
    	return this.userRepository.findById(id).get();
    }
    
    @Transactional
    public void delete(User user) {
    	this.userRepository.delete(user);
    }
    
    public List<User> findAllDriver(){
		List<User> drivers = new ArrayList<>();
		for (User driver : this.userRepository.findAll() ) {
			if(driver.isDriver())
				drivers.add(driver);
		}
		return drivers;
	}
    
    public User findByNomeAndCognome(String nome, String cognome) {
    	return this.userRepository.findByNomeAndCognome(nome, cognome);
    }


    @Transactional
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.userRepository.findAll();
        for(User user : iterable)
            result.add(user);
        return result;
    }
}
