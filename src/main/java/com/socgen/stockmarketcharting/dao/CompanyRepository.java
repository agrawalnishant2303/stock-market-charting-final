package com.socgen.stockmarketcharting.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socgen.stockmarketcharting.entity.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>{

	Company findByCompanyName(String companyName);

	List<Company> findAllBySectorName(String string);

	//Company findByCompanyCode(String companycode);

}
