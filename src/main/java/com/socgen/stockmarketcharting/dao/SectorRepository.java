package com.socgen.stockmarketcharting.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.socgen.stockmarketcharting.entity.Sector;


public interface SectorRepository extends JpaRepository<Sector,Long>{

	Sector findBySectorName(String sectorName);

	

	

}
