package com.tio.net.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tio.net.model.Student;
import com.tio.net.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService service;

	@PostMapping("/student")
	@ResponseStatus(HttpStatus.OK)
	public Student addStudent(@RequestBody Student student) {
		return service.addStudent(student);
	}

	@PutMapping("/student/{studentId}")
	@ResponseStatus(HttpStatus.OK)
	public Student updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student) {
		student.setId(studentId);
		return service.updateStudent(student);
	}

	@GetMapping("/student")
	@ResponseStatus(HttpStatus.OK)
	public List<Student> getAllStudent() {
		return service.getAllStudent();
	}

	@GetMapping("/student/{studentId}")
	@ResponseStatus(HttpStatus.OK)
	public Student getStudentById(@PathVariable("studentId") Long id) {
		return service.getStudentById(id);
	}

	@DeleteMapping("/student/{studentId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteStudentById(@PathVariable("studentId") Long studentId) {
		service.deleteStudentById(studentId);
	}

}
