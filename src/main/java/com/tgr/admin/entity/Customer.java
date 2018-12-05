package com.tgr.admin.entity;


import javax.persistence.*;



/**
 * 
 * 企业客户
 *
 */
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity{

	private static final long serialVersionUID = -1779924465835448992L;

	@Column(name = "name")
	private String name;

	@Column(name = "reserve")
	private String reserve;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	
	
}
