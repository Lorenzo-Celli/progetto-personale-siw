package it.uniroma3.siw.spring.furgoni.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Rotta> rotte;
	
	@OneToOne
	private Credentials credenziali;
	
	public User () {
		this.rotte = new ArrayList<>();
	}
	
	public List<Rotta> getRotte() {
		return rotte;
	}

	public void setRotte(List<Rotta> rotte) {
		this.rotte = rotte;
	}

	public Credentials getCredenziali() {
		return credenziali;
	}

	public void setCredenziali(Credentials credenziali) {
		this.credenziali = credenziali;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}
