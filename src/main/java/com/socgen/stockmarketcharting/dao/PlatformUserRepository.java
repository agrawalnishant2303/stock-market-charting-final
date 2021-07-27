package com.socgen.stockmarketcharting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socgen.stockmarketcharting.entity.PlatformUser;

public interface PlatformUserRepository extends JpaRepository<PlatformUser,Long> {

}
