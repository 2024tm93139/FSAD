package com.bits.fsd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationDriveForm {
	private String vaccineName;
	private String driveDate;
	private short doses;
}
