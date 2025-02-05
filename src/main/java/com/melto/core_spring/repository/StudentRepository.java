package com.melto.core_spring.repository;

import com.melto.core_spring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByFirstNameContaining(String name);
}
