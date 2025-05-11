package com.bits.fsd.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bits.fsd.bean.VaccineForm;
import com.bits.fsd.model.Vaccine;
import com.bits.fsd.services.VaccineService;

@RestController
@RequestMapping(value = "/vaccine")
public class VaccinationController {
	@Autowired
	private VaccineService vaccineService;
	
	@GetMapping
	public Vaccine getVaccineById(@RequestParam Integer vaccineId) {
		return vaccineService.getVaccineForId(vaccineId);
	}
	
	@PostMapping(value = "create")
	public boolean createVaccine(VaccineForm form) {
		Vaccine vaccine=vaccineService.createVaccine(form.getVaccineName());
		return Objects.nonNull(vaccine);
	}
	
}
