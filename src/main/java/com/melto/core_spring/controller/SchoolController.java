package com.melto.core_spring.controller;

import com.melto.core_spring.dto.Schooldto;
import com.melto.core_spring.model.School;
import com.melto.core_spring.model.Student;
import com.melto.core_spring.repository.SchoolRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}")
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public Schooldto create(@RequestBody Schooldto schooldto) {
        var school = toSchooldto(schooldto);
        var response =  schoolRepository.save(school);
        return toResponsedto(response);
    }

    private School toSchooldto(Schooldto schooldto) {
        var school = new School();
        school.setName(schooldto.name());
        return school;
    }

    private Schooldto toResponsedto(School school) {
        return new Schooldto(
                school.getName()
        );
    }

    @GetMapping("/schools")
    public List<Schooldto> getSchool() {
        return schoolRepository.findAll()
                .stream()
                .map(this::toGetSchooldto)
                .collect(Collectors.toList());
    }

    private Schooldto toGetSchooldto(School school) {
        return new Schooldto(school.getName());
    }
}
