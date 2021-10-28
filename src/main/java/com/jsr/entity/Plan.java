package com.jsr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "plan_details")
@Data
public class Plan {
	
	@Id
	@GeneratedValue
	@Column(name = "plan_id")
	private Integer plan_id;
	
	@Column(name = "plan_name")
	private String plan_name;
	
	@Column(name = "plan_status")
	private String plan_status;
	
	@Column(name = "plan_sdate")
	private String plan_sdate;
	
	@Column(name = "plan_edate")
	private String plan_edate;
	
	@Column(name = "benefit_amt")
	private double benefit_amt;
}
