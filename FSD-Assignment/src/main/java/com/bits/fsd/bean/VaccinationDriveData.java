package com.bits.fsd.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class VaccinationDriveData {
	private Integer id;
	private String driveDate;
	private String vaccineName;
	private short availableDoses;
}
