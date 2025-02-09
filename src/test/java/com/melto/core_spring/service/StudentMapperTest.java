package com.melto.core_spring.service;

import com.melto.core_spring.dto.StudentResponsedto;
import com.melto.core_spring.dto.Studentdto;
import com.melto.core_spring.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentdtoToStudent() {
        Studentdto studentdto = new Studentdto(
                "shehin",
                "melto",
                "meltosm8@gmail.com",
                22,
                1L
        );

        Student student = mapper.toStudent(studentdto);

        assertEquals(studentdto.firstName(), student.getFirstName());
        assertEquals(studentdto.lastName(), student.getLastName());
        assertEquals(studentdto.email(), student.getEmail());
        assertEquals(studentdto.age(), student.getAge());
        assertNotNull(student.getSchool());
        assertEquals(studentdto.id(), student.getSchool().getId());
    }

    @Test
    public void check_studentdto_to_student_null_exception() {
        var msg = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("Student dto should not be null", msg.getMessage());
    }

    @Test
    public void studentResponseToTest() {
        Student student = new Student("shehin", "melto", "melto@gmail.com", 22);
        StudentResponsedto response = mapper.toStudentResponse(student);

        assertEquals(response.firstName(), student.getFirstName());
        assertEquals(response.lastName(), student.getLastName());
        assertEquals(response.email(), student.getEmail());
    }
}