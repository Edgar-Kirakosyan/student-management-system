package com.sms.student_management_system.service;

import com.sms.student_management_system.entity.MyAppUser;
import com.sms.student_management_system.exception.UserIsAlreadyPresentException;
import com.sms.student_management_system.repository.MyAppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MyAppUserService implements UserDetailsService {

    private final MyAppUserRepository repository;

    public MyAppUserService(MyAppUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyAppUser> user = repository.findByUsername(username);

        if(user.isPresent()) {
            MyAppUser userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public boolean isTheUsernamePresent(String username) {
        Optional<MyAppUser> user = repository.findByUsername(username);
        return user.isPresent();
    }

    public MyAppUser signUp(MyAppUser user) throws UserIsAlreadyPresentException {
        if(isTheUsernamePresent(user.getUsername()))
            throw new UserIsAlreadyPresentException();
        return user;
    }

    //for removing duplicate users, used once
//    @PostConstruct
//    public void removeDuplicateUsers() {
//        List<MyAppUser> users = repository.findAll();
//
//        Map<String, MyAppUser> uniqueUsers = new HashMap<>();
//
//        for (MyAppUser user : users) {
//            String username = user.getUsername();
//            if (!uniqueUsers.containsKey(username)) {
//                uniqueUsers.put(username, user);
//            } else {
//                repository.delete(user); // deletes duplicates
//            }
//        }
//
//        System.out.println("Duplicate users removed.");
//    }
}
