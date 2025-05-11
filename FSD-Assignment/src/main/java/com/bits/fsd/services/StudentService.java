package com.bits.fsd.services;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.bits.fsd.model.Student;
import com.bits.fsd.model.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public Student getStudentForId(Integer id) {
		if (id == null) {
			return null;
		}
		Optional<Student> studentOptional = studentRepository.findById(id);
		if (studentOptional.isPresent()) {
			return studentOptional.get();
		}
		return null;
	}

	public Integer createStudent(Map<String, Object> request) {
		Student student = new Student();
		student.setName((String) request.get("name"));
		student.setRollNo(Integer.parseInt((String) request.get("rollNo")));
		student.setVaccinated((boolean) request.get("vaccinated"));

		Student existingStudent = this.checkStudentStatus(student);
		if (Objects.nonNull(existingStudent)) {
			return existingStudent.getId();
		}
		Student savedStudent = studentRepository.save(student);
		if (Objects.nonNull(savedStudent)) {
			return savedStudent.getId();
		}
		return 0;
	}

	public boolean markStudentVaccinated(Integer studentId, Integer driveId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if (student.isEmpty()) {
			return false;
		}
		Student studentModel = student.get();
		studentModel.setVaccinated(true);
		studentRepository.save(studentModel);
		return true;
	}

	public Student checkStudentStatus(Student student) {
		Example<Student> stdentExample = Example.of(student);
		Optional<Student> optionalStudent = studentRepository.findOne(stdentExample);
		if (optionalStudent.isPresent()) {
			return optionalStudent.get();
		}
		return null;
	}

	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	public List<Student> getAllVaccinatedStudent() {
		Student student = new Student();
		student.setVaccinated(Boolean.TRUE);
		Example<Student> exampleStudent = Example.of(student);
		return studentRepository.findAllVaccinatedStudent(exampleStudent);
	}

	public void updateStudent(Map<String, Object> studentForm) {
		Integer id = (Integer) studentForm.get("id");
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			Student model = student.get();
			if (StringUtils.isNotBlank((String) studentForm.get("name")))
				model.setName((String) studentForm.get("name"));
			if (StringUtils.isNotBlank((String) studentForm.get("rollno")))
				model.setRollNo((Integer) studentForm.get("rollno"));
			if (StringUtils.isNotBlank((String) studentForm.get("status")))
				model.setVaccinated((boolean) studentForm.get("status"));
			studentRepository.save(model);
		}

	}

	public void performBulkImport(String file) {
		String[] studentDetails = file.split("\r\n");
		for (int i = 1; i < studentDetails.length; i++) {
			String[] studentarr = studentDetails[i].split(",");
			String name = studentarr[0];
			Integer rollNo = Integer.valueOf(studentarr[1]);
			Student student = new Student(null, name, rollNo,
					Boolean.valueOf(studentarr[2]));
			studentRepository.save(student);
		}
	}
}
