package com.bits.fsd.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student2VaccineRel {
	@Id
	@Column(nullable = false)
	public Integer uid;
	@Column(nullable = false, unique = true)
	public Integer studentId;
	@Column(nullable = false, unique = true)
	public String vaccineName;
}
