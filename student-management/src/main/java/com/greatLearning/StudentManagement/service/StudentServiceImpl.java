package com.greatLearning.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatLearning.StudentManagement.entity.Student;
import com.greatLearning.StudentManagement.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository Repository;

	@Override
	public List<Student> findAll() {
		List<Student> students = Repository.findAll();
		return students;
	}

	@Override
	public Student findById(int theId) {
		return Repository.findById(theId).get();
	}

	@Override
	public void save(Student student) {
		Repository.save(student);

	}

	@Override
	public void deleteById(int theId) {
		Repository.deleteById(theId);

	}

}