package com.tio.net.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tio.net.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Transactional
	Long deleteById(Long studentId);

	Student findStudentByEmail(String email);

	Student findStudentByMobile(String mobile);

	Student findStudentByPhone(String phone);
}
