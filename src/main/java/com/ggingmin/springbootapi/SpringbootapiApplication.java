package com.ggingmin.springbootapi;

import com.ggingmin.springbootapi.model.Student;
import com.ggingmin.springbootapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringbootapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootapiApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {

		try {
			Student student = new Student();
			student.setFirstName("KYEONGMIN");
			student.setLastName("CHO");
			student.setEmailId("ggigmin@example.com");
			studentRepository.save(student);

			Student student2 = new Student();
			student2.setFirstName("TESTER");
			student2.setLastName("KIM");
			student2.setEmailId("tester@example.com");
			studentRepository.save(student2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
