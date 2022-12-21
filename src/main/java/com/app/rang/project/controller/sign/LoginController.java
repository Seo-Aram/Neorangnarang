package com.app.rang.project.controller.sign;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin() {
        return "view/sign/login";
    }
}
