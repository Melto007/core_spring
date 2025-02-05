package com.melto.core_spring.repository;

import com.melto.core_spring.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
