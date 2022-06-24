package it.uniroma3.siw.spring.furgoni.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rifornimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String data;
	
	private Double importo;
	
	@ManyToOne
	private Furgone furgone;
	
	@OneToOne
	private Rotta rotta;
	

	public Rotta getRotta() {
		return rotta;
	}

	public void setRotta(Rotta rotta) {
		this.rotta = rotta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}


	public Furgone getFurgone() {
		return furgone;
	}

	public void setFurgone(Furgone furgone) {
		this.furgone = furgone;
	}
	
	
	
}
