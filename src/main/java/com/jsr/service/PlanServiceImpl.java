package com.jsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsr.entity.Plan;
import com.jsr.repo.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanRepository planRepo;
	
	@Override
	public List<Plan> getDetails() {
		
		return planRepo.findAll();
		
	}

}
