package com.bits.fsd.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Vaccine")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "vaccine_id_generator", initialValue = 1000)
public class Vaccine {
	@Id
	@Column(nullable = false, name = "vaccine_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vaccine_id_generator")
	private Integer id;
	@Column(nullable = false, name = "vaccine_name")
	private String name;
	@Column
	private List<Integer> vaccinationDrive;
}
