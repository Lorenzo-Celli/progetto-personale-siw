package it.uniroma3.siw.spring.furgoni.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rotta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String codice;
	
	private Date data;
	
	private Double kmIniziali;
	
	private Double kmFinali;
	
	@ManyToOne
	private Furgone furgone;
	
	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getKmIniziali() {
		return kmIniziali;
	}

	public void setKmIniziali(Double kmIniziali) {
		this.kmIniziali = kmIniziali;
	}

	public Double getKmFinali() {
		return kmFinali;
	}

	public void setKmFinali(Double kmFinali) {
		this.kmFinali = kmFinali;
	}

	public Furgone getFurgone() {
		return furgone;
	}

	public void setFurgone(Furgone furgone) {
		this.furgone = furgone;
	}
	
	
	
}
