package it.uniroma3.siw.spring.furgoni.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rotta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	private String data;
	
	private double kmIniziali;
	
	private double kmFinali;

	/**
	 * il cascade lho messo sennò quando lo rendo persistente i dice che prima devo salvare un furgone,
	 * ma se salvo prima il furgone mi da lo stesso problema al contrario,
	 * essendo il primo ho messo il cascade così in automatico non mi da prblemi per testare
	 */
	@ManyToOne(cascade = CascadeType.PERSIST) 
	private Furgone furgone;
	
	// Stesso ragionamento
	@OneToOne(cascade = CascadeType.PERSIST)
	private Rifornimento rifornimento;
	
	@ManyToOne
	private User user;

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
	
	public User getUser() {
		return user;
	}
	public Rifornimento getRifornimento() {
		return rifornimento;
	}

	public void setRifornimento(Rifornimento rifornimento) {
		this.rifornimento = rifornimento;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
