package com.socgen.stockmarketcharting.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="IpoDetail")
@Getter
@Setter
@NamedQuery(name="IpoDetail.getDate", query = "SELECT c FROM IpoDetail c WHERE c.openDateTime >= :date")
public class IpoDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private Double pricePerShare;
	@Column(nullable=false)
	private Long totalnumberOfShares;
	@Column(nullable=false)
	private LocalDateTime openDateTime;
	@Column(nullable=false)
	private String remarks;
	private String companyName;
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Company company;
	@ManyToMany
	@JsonIgnore
	private List<StockExchange> stockExchanges = new ArrayList<>();
	protected IpoDetail() {
		
	}
	public IpoDetail(Double pricePerShare, Long totalnumberOfShares, LocalDateTime openDateTime, String remarks) {
		super();
		this.pricePerShare = pricePerShare;
		this.totalnumberOfShares = totalnumberOfShares;
		this.openDateTime = openDateTime;
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "IpoDetail [pricePerShare=" + pricePerShare + ", totalnumberOfShares=" + totalnumberOfShares
				+ ", openDateTime=" + openDateTime + ", remarks=" + remarks + ", companyName=" + companyName + "]";
	}

	
	
}
