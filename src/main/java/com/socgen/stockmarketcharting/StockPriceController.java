package com.socgen.stockmarketcharting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.stockmarketcharting.dao.CompanyRepository;
import com.socgen.stockmarketcharting.dao.CompanyStockExchangeMapRepository;
import com.socgen.stockmarketcharting.dao.StockExchangeRepository;
import com.socgen.stockmarketcharting.dao.StockPriceRepository;
import com.socgen.stockmarketcharting.entity.Company;
import com.socgen.stockmarketcharting.entity.SectorPrice;
import com.socgen.stockmarketcharting.entity.StockExchange;
import com.socgen.stockmarketcharting.entity.StockPrice;

@RestController
@CrossOrigin
public class StockPriceController {
	
	@Autowired
	CompanyRepository compRepo;
	@Autowired
	StockExchangeRepository stockRepo;
	@Autowired
	CompanyStockExchangeMapRepository csemRepo;
	@Autowired
	StockPriceRepository priceRepo;
	@Autowired
	EntityManager em;
	
	List<StockPrice> getDataFromDB(String code, String exchange, LocalDate localDate, LocalDate localDate2){
		Query query = em.createNamedQuery("StockPrice.getDate");
		query.setParameter("startDate",localDate);
		query.setParameter("endDate",localDate2);
		List<StockPrice> stock = (List<StockPrice>) query.getResultList();
		List<StockPrice> filteredStock = new ArrayList<>();
		for(StockPrice s:stock) {
			if(s.getCompanycode().equals(code) && s.getExchangename().equals(exchange)) {
				filteredStock.add(s);
				}
		}
		return filteredStock;
		}
//	List<SectorPrice> getDataforSector(String exchange,String sectorName, LocalDate localDate,LocalDate localDate2){
//		Query query = em.createNamedQuery("StockPrice.getDate");
//		query.setParameter("startDate",localDate);
//		query.setParameter("endDate",localDate2);
//		int number=0;
//		List<StockPrice> stock = (List<StockPrice>) query.getResultList();
//		List<StockPrice> filteredStock = new ArrayList<>();
//		SectorPrice sectorPrice = new SectorPrice();
//		List<SectorPrice> filteredShare = new ArrayList<>();
//		for(StockPrice s:stock) {
//			if(s.getCompany().getSectorName()==sectorName && s.getExchangename().equals(exchange)) {
//				filteredStock.add(s);
//			}
//		}
//		LocalDate date = filteredStock.get(0).getDatee();
//		float StockPrice = 0;
//		for(StockPrice s:filteredStock) {
//			while(s.getDatee() == date) {
//				StockPrice += s.getShareprice(); 
//				number++;
//			}
//			
//			sectorPrice.setSectorPrice(StockPrice/number);
//			filteredShare.add(sectorPrice);
//			date = s.getDatee(); 
//		}
//		return filteredShare;
//	}
	@RequestMapping(value="/getsinglecompany", method = RequestMethod.POST)
	public Map<String,List<StockPrice>> getSingleCompany(@RequestBody Map<String,String> text){
		//company name, stock exchange, from and to 1, from and to 2
		StockExchange e = stockRepo.findByName(text.get("name"));
		Company c = compRepo.findByCompanyName(text.get("companyName"));
		Query query = em.createNamedQuery("CompanyStockExchange.findBycompanyCode");
		query.setParameter("stockexchange",e);
		query.setParameter("company",c);
		String cCode = (String) query.getSingleResult();
		List<StockPrice> period1 = getDataFromDB(cCode,text.get("name"),LocalDate.parse(text.get("from1")),LocalDate.parse(text.get("to1")));
		List<StockPrice> period2 = getDataFromDB(cCode,text.get("name"),LocalDate.parse(text.get("from2")),LocalDate.parse(text.get("to2")));
		Map<String,List<StockPrice>> map =new HashMap<>();
		map.put("firstList",period1);
		map.put("secondList",period2);
		return map;
	}
	@RequestMapping(value="/getonecompany", method = RequestMethod.POST)
	public List<StockPrice> getOneCompany(@RequestBody Map<String,String> text){
		//company name, stock exchange, from and to 1, from and to 2
		StockExchange e = stockRepo.findByName(text.get("name"));
		Company c = compRepo.findByCompanyName(text.get("companyName"));
		Query query = em.createNamedQuery("CompanyStockExchange.findBycompanyCode");
		query.setParameter("stockexchange",e);
		query.setParameter("company",c);
		String cCode = (String) query.getSingleResult();
		List<StockPrice> period1 = getDataFromDB(cCode,text.get("name"),LocalDate.parse(text.get("from1")),LocalDate.parse(text.get("to1")));
		return period1;
	}
	@RequestMapping(value="/getallstockprice",method = RequestMethod.GET)
		public List<StockPrice> getallprice(){
			List<StockPrice> stockpricelist = priceRepo.findAll();
			return stockpricelist;
		}
	@RequestMapping(value="/getdifferentcompany", method = RequestMethod.POST)
	public Map<String,List<StockPrice>> getTwoCompany(@RequestBody Map<String,String> text){
		//company name, stock exchange, from and to 1, from and to 2
		StockExchange e = stockRepo.findByName(text.get("name"));
		Company c1 = compRepo.findByCompanyName(text.get("companyName1"));
		Company c2 = compRepo.findByCompanyName(text.get("companyName2"));
		Query query1 = em.createNamedQuery("CompanyStockExchange.findBycompanyCode");
		query1.setParameter("stockexchange",e);
		query1.setParameter("company",c1);
		String cCode1 = (String) query1.getSingleResult();
		Query query2 = em.createNamedQuery("CompanyStockExchange.findBycompanyCode");
		query2.setParameter("stockexchange",e);
		query2.setParameter("company",c2);
		String cCode2 = (String) query2.getSingleResult();
		List<StockPrice> period1 = getDataFromDB(cCode1,text.get("name"),LocalDate.parse(text.get("from1")),LocalDate.parse(text.get("to1")));
		List<StockPrice> period2 = getDataFromDB(cCode2,text.get("name"),LocalDate.parse(text.get("from1")),LocalDate.parse(text.get("to1")));
		Map<String,List<StockPrice>> map =new HashMap<>();
		map.put("firstList",period1);
		map.put("secondList",period2);
		return map;
	}
//	@RequestMapping(value="/getsamesector", method = RequestMethod.POST)
//	public Map<String,List<StockPrice>> getSingleSector(@RequestBody Map<String,String> text){
//		List<SectorPrice> sectorPeriod1 = getDataFromSector(text.get("name"),text.get("sectorName))"
//		List<SectorPrice> sectorPeriod2 = 
//		
//	}
	
}
