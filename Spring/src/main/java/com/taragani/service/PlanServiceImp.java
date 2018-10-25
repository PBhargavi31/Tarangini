package com.taragani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taragani.dao.PlansRepo;
import com.taragani.model.Plan;

@Service
public class PlanServiceImp implements PlanService {
	
	@Autowired
	public PlansRepo plansRepo;

	@Override
	public boolean exists(String pTitle) {
		return plansRepo.existsById(pTitle);
	}

	@Override
	public Plan getPlan(String pTitle) {
		Optional<Plan> opt = plansRepo.findById(pTitle);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public List<Plan> getAllPlans() {
		List<Plan> plans = plansRepo.findAll();
		return plans;
	}

	@Override
	public List<Plan> getPlansBySpeed(int speed) {
		List<Plan> plans = plansRepo.findAllBySpeed(speed);
		return plans;
	}

	@Override
	public List<Plan> getPlansByUsage(int maxUsage) {
		List<Plan> plans = plansRepo.findAllByMaxUsage(maxUsage);
		return plans;
	}

}