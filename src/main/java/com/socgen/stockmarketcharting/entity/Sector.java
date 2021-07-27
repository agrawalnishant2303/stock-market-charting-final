package com.socgen.stockmarketcharting.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="sector")
@Getter
@Setter
public class Sector {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String sectorName;
	@Column(nullable=false)
	private String brief;
	@OneToMany(mappedBy = "sector",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Company> companies = new ArrayList<>();
		
	protected Sector() {
		
	}

	public Sector(String sectorName, String brief) {
		super();
		this.sectorName = sectorName;
		this.brief = brief;
	}
	
}
