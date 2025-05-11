package com.bits.fsd.model;

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
@Table(name = "Student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "student_id_generator", initialValue = 100000, allocationSize = 1)
public class Student {
	@Id
	@Column(nullable = false, name = "student_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_generator")
	private Integer id;
	@Column(nullable = false, name = "student_name")
	private String name;
	@Column(nullable = false, name = "student_roll_number")
	private int rollNo;
	@Column
	private boolean vaccinated;
}
