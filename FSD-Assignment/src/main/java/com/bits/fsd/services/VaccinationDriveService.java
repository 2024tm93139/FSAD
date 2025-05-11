package com.bits.fsd.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.bits.fsd.bean.DriveUpdateForm;
import com.bits.fsd.bean.VaccinationDriveData;
import com.bits.fsd.model.VaccinationDrive;
import com.bits.fsd.model.repository.VaccinationDriveRespository;

@Service
public class VaccinationDriveService {
	@Autowired
	private VaccinationDriveRespository vaccinationDriveRespository;

	public VaccinationDrive createVaccinationDrive(String vaccineName, String driveDate, short doses) throws ParseException {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formate.parse(driveDate);
		VaccinationDrive drive = new VaccinationDrive(null, date, vaccineName, doses);
		Example<VaccinationDrive> example = Example.of(drive);
		Optional<VaccinationDrive> optionalExistingDrive = vaccinationDriveRespository.findOne(example);
		if (optionalExistingDrive.isPresent()) {
			return optionalExistingDrive.get();
		}
		vaccinationDriveRespository.save(drive);
		return drive;
	}

	public List<VaccinationDriveData> getUpcomingDrives() {
		Calendar cal = Calendar.getInstance();
		Date currentDate = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		Date finalDate = cal.getTime();
		List<VaccinationDrive> drives = vaccinationDriveRespository.findAll(currentDate, finalDate);
		if (CollectionUtils.isEmpty(drives)) {
			return Collections.emptyList();
		}

		return convertToData(drives);
	}

	private List<VaccinationDriveData> convertToData(List<VaccinationDrive> eligibleDrives) {
		List<VaccinationDriveData> dataList = new ArrayList<>();
		SimpleDateFormat formate = new SimpleDateFormat("dd-MMMM-yyyy");
		eligibleDrives.forEach(c -> {
			dataList.add(new VaccinationDriveData(c.getId(), formate.format(c.getDriveDate()), c.getVaccineName(),c.getAvailableDose()));
		});
		return dataList;
	}

	public List<VaccinationDriveData> getAllUpcomingDrive() {
		Date currentDate = Calendar.getInstance().getTime();
		System.out.println(currentDate);
		return convertToData(vaccinationDriveRespository.getAllUpcomingDrive(currentDate));
	}

	public void updateVaccinationDrive(DriveUpdateForm form) {
		Optional<VaccinationDrive> driveOptional = vaccinationDriveRespository.findById(form.getId());
		if (!driveOptional.isPresent()) {
			return;
		}
		VaccinationDrive drive = driveOptional.get();
		if (!drive.getVaccineName().equals(form.getName())) {
			drive.setVaccineName(form.getName());
		}
		if (!drive.getDriveDate().equals(form.getDate())) {
			drive.setDriveDate(form.getDate());
		}
		vaccinationDriveRespository.save(drive);

	}
}
