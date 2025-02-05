package com.melto.core_spring.controller;

import com.melto.core_spring.dto.StudentResponsedto;
import com.melto.core_spring.dto.Studentdto;
import com.melto.core_spring.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponsedto createStudent(@RequestBody Studentdto studentdto) {
        return studentService.saveStudent(studentdto);
    }

    @GetMapping("students")
    public List<StudentResponsedto> getStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("students/{student_id}")
    public StudentResponsedto getStudentById(@PathVariable("student_id") Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("students/search/{first_name}")
    public List<StudentResponsedto> getStudentsByName(@PathVariable("first_name") String name) {
        return studentService.getStudentsByName(name);
    }

    @DeleteMapping("students/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student_id") Long id) {
        studentService.deleteStudent(id);
    }
}
