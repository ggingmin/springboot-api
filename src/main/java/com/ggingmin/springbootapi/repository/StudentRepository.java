package com.ggingmin.springbootapi.repository;

import com.ggingmin.springbootapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
