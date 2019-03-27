package com.tio.net.service;

import java.util.List;

import com.tio.net.model.Student;

public interface StudentService {

	Student addStudent(Student student);

	Student updateStudent(Student student);

	List<Student> getAllStudent();

	Student getStudentById(Long id);

	void deleteStudentById(Long studentId);

}
