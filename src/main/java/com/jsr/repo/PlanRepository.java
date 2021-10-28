package com.jsr.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsr.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Serializable> {

}
