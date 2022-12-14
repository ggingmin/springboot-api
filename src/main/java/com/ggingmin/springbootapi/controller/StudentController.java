package com.ggingmin.springbootapi.controller;

import com.ggingmin.springbootapi.exception.ResourceNotFoundException;
import com.ggingmin.springbootapi.model.Student;
import com.ggingmin.springbootapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        try {
            Student student = studentRepository
                    .findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " does not exist"));
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student student) {
        try {
            Student targetStudent = studentRepository
                    .findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " does not exist"));
            targetStudent.setFirstName(student.getFirstName());
            targetStudent.setLastName(student.getLastName());
            targetStudent.setEmailId(student.getEmailId());

            studentRepository.save(targetStudent);

            return ResponseEntity.ok(targetStudent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id) {
        try {
            Student student = studentRepository
                    .findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " does not exist"));
            studentRepository.delete(student);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
