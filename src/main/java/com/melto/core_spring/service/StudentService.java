package com.melto.core_spring.service;

import com.melto.core_spring.dto.StudentResponsedto;
import com.melto.core_spring.dto.Studentdto;
import com.melto.core_spring.model.Student;
import com.melto.core_spring.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponsedto saveStudent(Studentdto studentdto) {
        var student = studentMapper.toStudent(studentdto);
        var response = studentRepository.save(student);
        return studentMapper.toStudentResponse(response);
    }

    public List<StudentResponsedto> getAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponse)
                .collect(Collectors.toList());
    }

    public StudentResponsedto getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponse)
                .orElse(null);
    }

    public List<StudentResponsedto> getStudentsByName(String name) {
        return studentRepository.findAllByFirstNameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponse)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
