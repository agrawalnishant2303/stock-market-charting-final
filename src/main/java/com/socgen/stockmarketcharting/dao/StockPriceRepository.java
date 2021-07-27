package com.socgen.stockmarketcharting.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socgen.stockmarketcharting.entity.Company;
import com.socgen.stockmarketcharting.entity.StockPrice;
@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice,Long> {



	void deleteAllByCompany(Optional<Company> companyToDelete);


}
