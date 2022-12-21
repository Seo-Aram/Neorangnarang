package com.app.rang.project.controller.sign;

import com.app.rang.project.model.user.SignupRequest;
import com.app.rang.project.service.sign.SignupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/register")
public class SignUpController {
    @Autowired
    SignupService signupService;

    @GetMapping
    public String getRegister(){
        return "view/sign/register";
    }

    @PostMapping
    public ResponseEntity<String> signup(
            @RequestBody SignupRequest signupRequest
    ){
        log.info(signupRequest);

        if((signupRequest.getUsername() == null  || signupRequest.getUsername().isEmpty())
                || (signupRequest.getPassword() == null  || signupRequest.getPassword().isEmpty())
                || (signupRequest.getNickname() == null  || signupRequest.getNickname().isEmpty())
                ) {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        int result = signupService.signup(signupRequest);

        return new ResponseEntity<>("/", new HttpHeaders(), HttpStatus.OK);
    }
}
