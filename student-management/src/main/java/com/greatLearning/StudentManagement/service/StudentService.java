package com.greatLearning.StudentManagement.service;

import java.util.List;
import com.greatLearning.StudentManagement.entity.Student;

public interface StudentService {
	public List<Student> findAll();

	public Student findById(int theId);

	public void save(Student theBook);

	public void deleteById(int theId);

}
