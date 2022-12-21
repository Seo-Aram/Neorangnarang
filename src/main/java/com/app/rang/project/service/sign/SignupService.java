package com.app.rang.project.service.sign;

import com.app.rang.project.model.user.SignupRequest;
import com.app.rang.project.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SignupService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public int signup(SignupRequest userData) {
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));

        return userRepository.save(userData.toUser())!= null ? 1 : 0;
    }
}
