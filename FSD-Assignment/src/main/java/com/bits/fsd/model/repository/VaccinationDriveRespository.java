package com.bits.fsd.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bits.fsd.model.VaccinationDrive;

@Repository
public interface VaccinationDriveRespository extends JpaRepository<VaccinationDrive, Integer>{

	@Query("SELECT v FROM VaccinationDrive AS v WHERE CAST(v.driveDate AS DATE)>=:time ORDER BY v.driveDate ASC")
	List<VaccinationDrive> getAllUpcomingDrive(@Param("time") Date time);

	@Query("SELECT v FROM VaccinationDrive AS v WHERE (v.driveDate >:currentDate AND v.driveDate<:finalDate) ORDER BY v.driveDate ASC")
	List<VaccinationDrive> findAll(Date currentDate, Date finalDate);

}
