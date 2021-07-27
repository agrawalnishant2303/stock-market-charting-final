package com.socgen.stockmarketcharting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.socgen.stockmarketcharting.dao.CompanyRepository;
import com.socgen.stockmarketcharting.dao.SectorRepository;
import com.socgen.stockmarketcharting.dao.StockExchangeRepository;
import com.socgen.stockmarketcharting.entity.Company;
import com.socgen.stockmarketcharting.entity.Sector;
import com.socgen.stockmarketcharting.entity.StockExchange;

@Configuration
public class loadData {

	private static final Logger log = LoggerFactory.getLogger(loadData.class);

	@Bean
	CommandLineRunner initDatabase(CompanyRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Company("Bilbo Baggins", 100, "f", "f", "f")));
			log.info("Preloading " + repository.save(new Company("Frodo Baggins", 100, "g", "h", "thief")));
		};

	}
	
	@Bean
	CommandLineRunner initDatabase3(SectorRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Sector("Bank","Test Bank Sector")));
			log.info("Preloading " + repository.save(new Sector("Electronics","Test Electronics Sector")));
		};

	}
	
	@Bean
	CommandLineRunner initDatabase4(StockExchangeRepository repository1) {

		return args -> {
			log.info("Preloading " + repository1.save(new StockExchange("BSE")));
			log.info("Preloading " + repository1.save(new StockExchange("NSE")));
		};

	}


//	@Bean
//	CommandLineRunner initDatabase2(PlatformUserRepository userrepo) {
//
//		return args -> {
//			log.info("Preloading " + userrepo.save(new PlatformUser("admin", "admin", "admin", true)));
//		};
//}
}