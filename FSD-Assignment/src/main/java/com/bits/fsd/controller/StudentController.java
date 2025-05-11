package com.bits.fsd.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bits.fsd.bean.VaccinationForm;
import com.bits.fsd.model.Student;
import com.bits.fsd.services.StudentService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping
	public ResponseEntity<Student> getStudent(@RequestParam Integer id) {
		Student student = studentService.getStudentForId(id);
		if (Objects.nonNull(student)) {
			return new ResponseEntity<>(student, HttpStatus.OK);
		}
		return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<String> createStudent(@RequestBody Map<String, Object> request) {
		String student = studentService.createStudent(request).toString();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@PostMapping("/mark_vaccinated")
	public boolean markStudentVaccinated(VaccinationForm form) {
		return studentService.markStudentVaccinated(form.getStudent_id(), form.getVaccine_id());
	}

	@PostMapping("/update")
	public void updateStudentDetails(@RequestBody Map<String, Object> studentForm) {
		studentService.updateStudent(studentForm);
	}

	@PostMapping("/file")
	public String updateStudentDetails(@RequestBody String file) {
		studentService.performBulkImport(file);
		return "Success";
	}

	@GetMapping("/report")
	public void downloadReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=report.xlsx");
		createActualData(response);
	}

	public void createActualData(HttpServletResponse response) throws IOException {
		List<Student> vaccinatedStudent = studentService.getAllVaccinatedStudent();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("VaccinatedStudent");

		Row firstrow = sheet.createRow(0);
		Cell cellName = firstrow.createCell(0);
		cellName.setCellValue("Student Name");
		Cell cellVaccinated = firstrow.createCell(1);
		cellVaccinated.setCellValue("Vaccinated");
		Cell cellRollNo = firstrow.createCell(2);
		cellRollNo.setCellValue("Roll no");

		for (int rowIndex = 1; rowIndex < vaccinatedStudent.size(); rowIndex++) {
			Row row = sheet.createRow(rowIndex);
			Student student = vaccinatedStudent.get(rowIndex);
			Cell firstcell = row.createCell(0);
			firstcell.setCellValue(student.getName());
			Cell secondcell = row.createCell(1);
			secondcell.setCellValue(Boolean.TRUE.equals(student.isVaccinated()) ? "TRUE" : "FALSE");
			Cell thirdcell = row.createCell(2);
			thirdcell.setCellValue(student.getRollNo());
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
