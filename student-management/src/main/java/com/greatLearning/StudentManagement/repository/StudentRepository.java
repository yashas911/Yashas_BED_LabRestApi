package com.greatLearning.StudentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatLearning.StudentManagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
