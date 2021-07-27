package com.socgen.stockmarketcharting.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SectorPrice {
	
	
	
	private float sectorPrice;
	private String sectorName;
	private LocalDate sectorDate;
	private String exchangeName;
}
