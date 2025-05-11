package com.bits.fsd.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DashboardData {
	private List<VaccinationDriveData> upcomingDrives;
	private String vaccinatedStudent;
	private String totalStudent;
	private String vaccinatedPercentage;
}