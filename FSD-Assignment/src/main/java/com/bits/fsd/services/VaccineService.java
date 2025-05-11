package com.bits.fsd.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.fsd.model.Vaccine;
import com.bits.fsd.model.repository.VaccineRepository;

@Service
public class VaccineService {
	@Autowired
	private VaccineRepository vaccineRepository;

	public Vaccine getVaccineForId(Integer id) {
		Optional<Vaccine> vaccine = vaccineRepository.findById(id);
		if (vaccine.isPresent()) {
			return vaccine.get();
		}
		return null;
	}

	public Vaccine createVaccine(String name) {
		Vaccine vaccine = new Vaccine();
		vaccine.setName(name);
		return vaccineRepository.save(vaccine);
	}

	public void saveVaccine(Vaccine vaccine) {
		vaccineRepository.save(vaccine);
	}
}
