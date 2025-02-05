package com.melto.core_spring.controller;

import com.melto.core_spring.dto.StudentResponsedto;
import com.melto.core_spring.dto.Studentdto;
import com.melto.core_spring.exceptions.StudentNotFound;
import com.melto.core_spring.model.School;
import com.melto.core_spring.model.Student;
import com.melto.core_spring.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    public StudentResponsedto createStudent(@RequestBody Studentdto studentdto) {
        var student = toStudent(studentdto);
        var response = studentRepository.save(student);
        return toStudentResponse(response);
    }

    private Student toStudent(Studentdto studentdto) {
        var student = new Student();
        student.setFirstName(studentdto.firstName());
        student.setLastName(studentdto.lastName());
        student.setEmail(studentdto.email());
        student.setAge(studentdto.age());

        var school = new School();
        school.setId(studentdto.id());

        student.setSchool(school);

        return student;
    }

    private StudentResponsedto toStudentResponse(Student student) {
        return new StudentResponsedto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getAge()
        );
    }

    @GetMapping("students")
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("students/{student_id}")
    public Student getStudentById(@PathVariable("student_id") Long id) {
        return studentRepository.findById(id).orElse(new Student());
    }

    @GetMapping("students/search/{first_name}")
    public List<Student> getStudentsByName(@PathVariable("first_name") String name) {
        return studentRepository.findAllByFirstNameContaining(name);
    }

    @DeleteMapping("students/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student_id") Long id) {
        studentRepository.deleteById(id);
    }
}
