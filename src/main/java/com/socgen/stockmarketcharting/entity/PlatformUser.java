package com.socgen.stockmarketcharting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PlatformUser")
@NamedQuery(name="PlatformUser.finduserbyemail", query="SELECT c FROM PlatformUser c WHERE c.email=:email AND c.password=:password ")
@Getter
@Setter
public class PlatformUser {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String password;
	private String email;
	private Boolean confirmed;
	private Boolean admin;
	private String role;
	public PlatformUser() {
		
	}
	public PlatformUser(String name, String password, String email, Boolean admin) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.admin = admin;
	}
	
}
