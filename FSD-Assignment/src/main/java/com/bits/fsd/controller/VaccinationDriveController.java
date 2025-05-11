package com.bits.fsd.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bits.fsd.bean.DashboardData;
import com.bits.fsd.bean.DriveUpdateForm;
import com.bits.fsd.bean.VaccinationDriveData;
import com.bits.fsd.enums.VaccinationDriveForm;
import com.bits.fsd.model.VaccinationDrive;
import com.bits.fsd.services.StudentService;
import com.bits.fsd.services.VaccinationDriveService;

@RestController
@RequestMapping(value = "/drive")
public class VaccinationDriveController {
	@Autowired
	private VaccinationDriveService vaccinationDriveService;
	@Autowired

	private StudentService studentService;

	@GetMapping
	public DashboardData getUpcomDrive() {
		List<VaccinationDriveData> upcomingDrives = vaccinationDriveService.getUpcomingDrives();
		Integer totalStudent = studentService.getAllStudent().size();
		Integer vaccinatedStudent = studentService.getAllVaccinatedStudent().size();
		String vaccinatedPercentage;
		if (totalStudent == 0 || vaccinatedStudent == 0) {
			vaccinatedPercentage = "0.00";
		} else {
			vaccinatedPercentage = String.format("%.2f",
					Double.valueOf(vaccinatedStudent) * 100 / Double.valueOf(totalStudent));
		}
		DashboardData data = new DashboardData(upcomingDrives, vaccinatedStudent.toString(), totalStudent.toString(),
				vaccinatedPercentage);
		return data;
	}

	@PostMapping(value = "/create")
	public VaccinationDrive createDrive(@RequestBody VaccinationDriveForm form) {
		try {
			return vaccinationDriveService.createVaccinationDrive(form.getVaccineName(), form.getDriveDate(),form.getDoses());
		} catch (ParseException e) {
			return null;
		}
	}
	
	@PostMapping(value = "/update")
	public void updateVaccinationDrive(@RequestBody DriveUpdateForm form) {
		vaccinationDriveService.updateVaccinationDrive(form);
	}
	
	
	@GetMapping(value="/all")
	public List<VaccinationDriveData> getAllUpcomingDrive(){
		return vaccinationDriveService.getAllUpcomingDrive();
	}
}
