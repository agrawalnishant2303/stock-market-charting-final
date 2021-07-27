package com.socgen.stockmarketcharting.entity;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@NamedQuery(name="Company.findByName",query="SELECT c FROM Company c WHERE c.companyName=:name")

@Entity
@Table(name="Company")
@Data
public class Company {
	
	
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String companyName;
	private String sectorName;
	@Column(nullable=false)
	private float turnover;
	@Column(nullable=false)
	private String ceo;
	@Column(nullable=false)
	@Type(type="text")
	private String boardOfDirectors;
	@Column(nullable=false)
	@Type(type="text")
	private String briefWriteup;
	@OneToOne(fetch = FetchType.LAZY, mappedBy="company", cascade=CascadeType.ALL)
	@JsonIgnore
	private IpoDetail ipo;
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Sector sector;
	@OneToMany(targetEntity = CompanyStockExchangeMap.class, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<CompanyStockExchangeMap> compStockmap = new ArrayList<>();
	protected Company() {
	}

	public Company(String companyName, float turnover, String ceo, String boardOfDirectors, String briefWriteup) {
		super();
		this.companyName = companyName;
		this.turnover = turnover;
		this.ceo = ceo;
		this.boardOfDirectors = boardOfDirectors;
		this.briefWriteup = briefWriteup;
	}

	@Override
	public String toString() {
		return "Company Name=" + companyName + "\nSector Name=" + sectorName + "\nTurnover=" + turnover
				+ "\nCEO= " + ceo + "\nBoard Of Directors=" + boardOfDirectors + "\nBrief Writeup=" + briefWriteup;
	}
	
	
}

