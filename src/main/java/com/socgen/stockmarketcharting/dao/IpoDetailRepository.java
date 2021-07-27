package com.socgen.stockmarketcharting.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socgen.stockmarketcharting.entity.Company;
import com.socgen.stockmarketcharting.entity.IpoDetail;
@Repository
public interface IpoDetailRepository extends JpaRepository <IpoDetail,Long> {

	IpoDetail findByCompanyName(String string);

	void deleteAllByCompany(Optional<Company> companyToDelete);

}
