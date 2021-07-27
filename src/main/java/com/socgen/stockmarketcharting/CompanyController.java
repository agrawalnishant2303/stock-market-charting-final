package com.socgen.stockmarketcharting;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socgen.stockmarketcharting.dao.CompanyRepository;
import com.socgen.stockmarketcharting.dao.CompanyStockExchangeMapRepository;
import com.socgen.stockmarketcharting.dao.IpoDetailRepository;
import com.socgen.stockmarketcharting.dao.SectorRepository;
import com.socgen.stockmarketcharting.dao.StockExchangeRepository;
import com.socgen.stockmarketcharting.dao.StockPriceRepository;
import com.socgen.stockmarketcharting.entity.Company;
import com.socgen.stockmarketcharting.entity.CompanyStockExchangeMap;
import com.socgen.stockmarketcharting.entity.IpoDetail;
import com.socgen.stockmarketcharting.entity.Sector;
import com.socgen.stockmarketcharting.entity.StockExchange;
import com.socgen.stockmarketcharting.entity.StockPrice;

@CrossOrigin
@RestController
public class CompanyController {

	@Autowired
	CompanyRepository compRepo;
	@Autowired
	StockExchangeRepository stockRepo;
	@Autowired
	SectorRepository secrepo;
	@Autowired
	CompanyStockExchangeMapRepository csemr;
	@Autowired
	SectorRepository sectorRepo;
	@Autowired
	StockPriceRepository stockPriceRepo;
	@Autowired
	EntityManager em;
	@Autowired
	IpoDetailRepository ipoRepo;

	@RequestMapping(value = "/company", method = RequestMethod.POST)
	public ResponseEntity<Object> createcompany(@RequestBody Company cmp) {
//input with be json properties 
		
		
		Sector se = sectorRepo.findBySectorName(cmp.getSectorName());
		cmp.setSector(se);
		compRepo.save(cmp);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cmp.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@RequestMapping(value = "/getallcompany",method=RequestMethod.GET )
	public List<Company> getallcompany() throws ClassNotFoundException, IOException {
		List<Company> companyAll= compRepo.findAll();
	   // make sure your entity class properties of user are in lower case and match the json,to avoid errors
	    return companyAll;
	}
	@RequestMapping(value = "/companydetails", method = RequestMethod.POST)
	public Company getCompanyDetails(@RequestBody Map<String,String> text) {
		Company c = compRepo.findByCompanyName(text.get("name"));
		return c;
	}
	@RequestMapping(value = "/companyipodetails", method = RequestMethod.POST)
	public String getCompanyIpoDetails(@RequestBody Map<String,String> text) {
		IpoDetail companyIpoDetail = ipoRepo.findByCompanyName(text.get("name"));
		return companyIpoDetail.toString();
	}
	@RequestMapping(value = "/sector", method = RequestMethod.POST)
	public ResponseEntity<Object> createsector(@RequestBody Sector sec) {
//input with be json properties 
		sectorRepo.save(sec);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sec.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	@RequestMapping(value="/companyinsector", method = RequestMethod.POST)
	public List<Company> companiesinSector(@RequestBody Map<String,String> text){
		List<Company> companyList = new ArrayList<>();
		companyList = compRepo.findAllBySectorName(text.get("sectorName"));
		return companyList;
	}
	@RequestMapping(value = "/stockexchange", method = RequestMethod.POST)
	public ResponseEntity<Object> createstockexchange(@RequestBody StockExchange stex) {
//input with be json properties 
		stockRepo.save(stex);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stex.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	@RequestMapping(value = "/getstockexchange", method = RequestMethod.GET)
	public List<StockExchange> getstockexchangelist() {
//input with be json properties 
		List<StockExchange>  stockExchangeList = stockRepo.findAll();
		return stockExchangeList;
	}
	@RequestMapping(value = "/companyinstockexchange", method = RequestMethod.POST)
	public List<Company> getcompanyinstockexchange(@RequestBody Map<String,String> text){
		StockExchange e = stockRepo.findByName(text.get("name"));
		Query query = em.createNamedQuery("CompanyStockExchange.findById");
		query.setParameter("stockexchange",e);
		List<Company> id = (List<Company>) query.getResultList();
		return id;
	}
	@RequestMapping(value = "/mapcompanycode", method = RequestMethod.POST)
//	// pass map of string in requestbody ,instead of pojo class to get
//	// non entity based params
//This method maps company to stockmarket	
	public String mapcode(@RequestBody Map<String, String> text) {
		System.out.println("params100" + text.get("companyName"));
		Query query = em.createNamedQuery("Company.findByName");
		query.setParameter("name", text.get("companyName"));
		Company c = (Company) query.getSingleResult();

		StockExchange e = stockRepo.findByName(text.get("name"));
		CompanyStockExchangeMap cse = new CompanyStockExchangeMap();
		cse.setCompany(c);
		cse.setStockexchange(e);
		cse.setCompanyCode(text.get("companycode"));
		csemr.save(cse);
		return "Test";
		// return companyname;
	}
	@RequestMapping(value="/listallmap",method = RequestMethod.GET)
	public List<CompanyStockExchangeMap> listmapall(){
		List<CompanyStockExchangeMap> mapAll= csemr.findAll();
		   // make sure your entity class properties of user are in lower case and match the json,to avoid errors
		    return mapAll;
	}
	// show all records from companystockexchangemap entity
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	public String listit() {

		String x = "";
		List<CompanyStockExchangeMap> csem = csemr.findAll();
		for (CompanyStockExchangeMap c : csem) {
			Optional<StockExchange> s = stockRepo.findById(c.getStockexchange().getId());
			Optional<Company> cc = compRepo.findById(c.getCompany().getId());
			x = x + "   " + cc.get().getCompanyName() + "   " + s.get().getName(); // getcompany code can be added here
		}

		return x;
		// return companyname;
	}
	@RequestMapping(value = "/stockprice", method = RequestMethod.POST)
	public ResponseEntity<Object> createprice(@RequestBody StockPrice stockPrice) {
//input with be json properties 
		
		CompanyStockExchangeMap csemm = csemr.findByCompanyCode(stockPrice.getCompanycode());
		
		Company c = csemm.getCompany();
		stockPrice.setCompany(c);
		stockPriceRepo.save(stockPrice);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stockPrice.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@RequestMapping(value="/ipodetails", method = RequestMethod.POST)
	public ResponseEntity<Object> createipo(@RequestBody IpoDetail ipo) {
		//input with be json properties 
		Company c = compRepo.findByCompanyName(ipo.getCompanyName());
		ipo.setCompany(c);
		ipoRepo.save(ipo);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ipo.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@RequestMapping(value = "/getipodetails",method=RequestMethod.GET )
	public List<IpoDetail> getIpoDetails() throws ClassNotFoundException, IOException {
		List<IpoDetail> ipoAll= ipoRepo.findAll();
	   // make sure your entity class properties of user are in lower case and match the json,to avoid errors
	    return ipoAll;
	}
	@GetMapping("/company/{id}")
	  public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
		Optional<Company> companyData = compRepo.findById(id);

	    if (companyData.isPresent()) {
	      return new ResponseEntity<>(companyData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	@PutMapping("/company/{id}")
	  public ResponseEntity<Company> updateTutorial(@PathVariable("id") long id, @RequestBody Company company) {
	    Optional<Company> companyData = compRepo.findById(id);

	    if (companyData.isPresent()) {
	      Company _company = companyData.get();
	      _company.setCompanyName(company.getCompanyName());
	      _company.setTurnover(company.getTurnover());
	      _company.setCeo(company.getCeo());
	      _company.setBoardOfDirectors(company.getBoardOfDirectors());
	      _company.setBriefWriteup(company.getBoardOfDirectors());
	      _company.setSectorName(company.getSectorName());
	      return new ResponseEntity<>(compRepo.save(_company), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/company/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	    	compRepo.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  @RequestMapping(value = "/getupcomingipodetails",method=RequestMethod.GET )
		public List<IpoDetail> getUpcomingIpoDetails() throws ClassNotFoundException, IOException {
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		  LocalDateTime now = LocalDateTime.now();
		  Query query = em.createNamedQuery("IpoDetail.getDate");
		  query.setParameter("date",now);
		  List<IpoDetail> upcomingipolist = (List<IpoDetail>) query.getResultList();
		   // make sure your entity class properties of user are in lower case and match the json,to avoid errors
		    return upcomingipolist;
		}
	  @RequestMapping(value = "/getallsector",method=RequestMethod.GET )
		public List<Sector> getSectorDetails() throws ClassNotFoundException, IOException {
			List<Sector> sectorAll= sectorRepo.findAll();
		   // make sure your entity class properties of user are in lower case and match the json,to avoid errors
		    return sectorAll;
		}

}
