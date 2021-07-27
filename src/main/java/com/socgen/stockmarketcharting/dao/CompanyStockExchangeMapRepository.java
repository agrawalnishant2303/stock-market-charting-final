package com.socgen.stockmarketcharting.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socgen.stockmarketcharting.entity.Company;
import com.socgen.stockmarketcharting.entity.CompanyStockExchangeMap;

public interface CompanyStockExchangeMapRepository extends JpaRepository<CompanyStockExchangeMap,Long> {


	CompanyStockExchangeMap findByCompanyCode(String companycode);

	List<CompanyStockExchangeMap> findAllByStockexchange(Long long1);

	void deleteByCompany(Optional<Company> companyToDelete);


	

}
