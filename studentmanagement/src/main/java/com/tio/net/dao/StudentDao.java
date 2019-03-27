package com.tio.net.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tio.net.exception.BadResourceRequestException;
import com.tio.net.exception.DuplicateEntryException;
import com.tio.net.exception.NoSuchResourceFoundException;
import com.tio.net.model.Student;
import com.tio.net.repository.StudentRepository;
import com.tio.net.service.StudentService;

@Service
public class StudentDao implements StudentService {

	@Autowired
	StudentRepository studentRepo;

	public Student addStudent(Student student) {
		if (StringUtils.isBlank(student.getName()))
			throw new BadResourceRequestException("Missing student name");
		validate(student);
		return studentRepo.save(student);

	}

	private Student validate(Student student) {
		Student existingStudent = null;
		if (StringUtils.isNotBlank(student.getEmail())) {
			existingStudent = studentRepo.findStudentByEmail(student.getEmail());
			if (existingStudent != null)
				throw new DuplicateEntryException("Email id is already registered");
		}
		if (StringUtils.isNotBlank(student.getMobile()) && existingStudent == null) {
			existingStudent = studentRepo.findStudentByMobile(student.getMobile());
			if (existingStudent != null)
				throw new DuplicateEntryException("Mobile number is already registered");
		}
		if (StringUtils.isNotBlank(student.getPhone()) && existingStudent == null) {
			existingStudent = studentRepo.findStudentByPhone(student.getPhone());
			if (existingStudent != null)
				throw new DuplicateEntryException("Phone number is already registered");
		}
		return existingStudent;

	}

	public Student updateStudent(Student student) {
		if (student.getId() == null)
			throw new BadResourceRequestException("Student Id must not be null");
		Student stud = studentRepo.findOne(student.getId());
		if (stud == null)
			throw new NoSuchResourceFoundException("Student not availabe for given Id");
		if (StringUtils.isNotBlank(student.getName()))
			stud.setName(student.getAddress());
		if (StringUtils.isNotBlank(student.getAddress()))
			stud.setAddress(student.getAddress());
		if (student.getDob() != null)
			stud.setDob(student.getDob());
		if (StringUtils.isNotBlank(student.getEmail()))
			stud.setEmail(student.getEmail());
		if (StringUtils.isNotBlank(student.getMobile()))
			stud.setMobile(student.getMobile());
		if (StringUtils.isNotBlank(student.getPhone()))
			stud.setPhone(student.getPhone());

		return studentRepo.saveAndFlush(student);
	}

	public List<Student> getAllStudent() {
		return studentRepo.findAll();
	}

	public Student getStudentById(Long id) {
		if (id == null)
			throw new BadResourceRequestException("Student Id must not be null");
		Student stud = studentRepo.findOne(id);
		if (stud == null)
			throw new NoSuchResourceFoundException("Student not availabe for given Id");
		return stud;
	}

	public void deleteStudentById(Long studentId) {
		studentRepo.deleteById(studentId);
	}

}
