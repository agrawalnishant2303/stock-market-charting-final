package com.socgen.stockmarketcharting.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="StockPrice")
@Getter
@Setter
@NamedQuery(name="StockPrice.getDate", query = "SELECT c FROM StockPrice c WHERE c.datee BETWEEN :startDate AND :endDate")
public class StockPrice {
	
	@Id
	@GeneratedValue
	private long id;
	private String companycode;
	private String exchangename;
	private float shareprice;
	private LocalDate datee;
	private LocalTime timee;
	private LocalDateTime localdatetime;
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Company company;
	public StockPrice(String companycode, String exchangename, float shareprice, LocalDate datee, LocalTime timee,
			LocalDateTime localdatetime) {
		super();
		this.companycode = companycode;
		this.exchangename = exchangename;
		this.shareprice = shareprice;
		this.datee = datee;
		this.timee = timee;
		this.localdatetime = localdatetime;
	}
	public StockPrice() {
		super();
	}

	
}
