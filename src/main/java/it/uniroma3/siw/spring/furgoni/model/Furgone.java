package it.uniroma3.siw.spring.furgoni.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Furgone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String targa;
	
	private Double kmAttuali;
	
	@OneToMany
	@JoinColumn(name = "furgone_id")
	private List<Rotta> rotte;
	
	@OneToMany
	@JoinColumn(name = "furgone_id")
	private List<Rifornimento> rifornimenti;
	
	public Furgone () {
		this.rotte = new ArrayList<>();
		this.rifornimenti = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Double getKmAttuali() {
		return kmAttuali;
	}

	public void setKmAttuali(Double kmAttuali) {
		this.kmAttuali = kmAttuali;
	}

	public List<Rotta> getRotte() {
		return rotte;
	}

	public void setRotte(List<Rotta> rotte) {
		this.rotte = rotte;
	}
	
	
	
}
