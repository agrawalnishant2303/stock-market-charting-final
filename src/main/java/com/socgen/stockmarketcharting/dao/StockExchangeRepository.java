package com.socgen.stockmarketcharting.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socgen.stockmarketcharting.entity.StockExchange;
@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange,Long> {

	StockExchange findByName(String string);

	


}
