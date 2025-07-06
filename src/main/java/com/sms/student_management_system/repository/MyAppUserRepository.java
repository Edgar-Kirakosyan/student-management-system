package com.sms.student_management_system.repository;

import com.sms.student_management_system.entity.MyAppUser;
import com.sms.student_management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyAppUserRepository extends JpaRepository<MyAppUser, Long> {
    Optional<MyAppUser> findByUsername(String username);
    List<MyAppUser> findByRole(Role role);
}
