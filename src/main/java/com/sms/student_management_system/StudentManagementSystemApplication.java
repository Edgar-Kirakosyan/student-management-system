package com.sms.student_management_system;

import com.sms.student_management_system.entity.Student;
import com.sms.student_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	//Testing the mySql part
	@Override
	public void run(String... args) throws Exception {
//		Student st1 = new Student("Edgar", "Kirakosyan", "edgar_kirakosyan@edu.aua.am");
//		studentRepository.save(st1);
//
//		Student st2 = new Student("Serob", "Kirakosyan", "serob_kirakosyan@edu.aua.am");
//		studentRepository.save(st2);
//
//		Student st3 = new Student("Lusya", "Stark", "lusya_stark@edu.aua.am");
//		studentRepository.save(st3);
	}
}
