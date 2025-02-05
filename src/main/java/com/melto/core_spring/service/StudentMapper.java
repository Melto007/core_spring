package com.melto.core_spring.service;

import com.melto.core_spring.dto.StudentResponsedto;
import com.melto.core_spring.dto.Studentdto;
import com.melto.core_spring.model.School;
import com.melto.core_spring.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(Studentdto studentdto) {
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

    public StudentResponsedto toStudentResponse(Student student) {
        return new StudentResponsedto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getAge()
        );
    }
}
