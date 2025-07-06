package com.sms.student_management_system;

import com.sms.student_management_system.entity.Student;
import com.sms.student_management_system.repository.MyAppUserRepository;
import com.sms.student_management_system.repository.StudentRepository;
import com.sms.student_management_system.service.MyAppUserService;
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

	@Autowired
	private MyAppUserService myAppUserService;

	//Testing the mySql part
	@Override
	public void run(String... args) throws Exception {
		//myAppUserService.removeDuplicateUsers();
	}


}
