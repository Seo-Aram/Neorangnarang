package com.app.rang.project.controller.user;

import com.app.rang.project.model.AuthUserDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping
    public String getMyPage(
            @AuthenticationPrincipal AuthUserDTO userDTO,
            Model model
    ){
        model.addAttribute("username", userDTO.getUsername());
        model.addAttribute("nickname", userDTO.getNickname());
        model.addAttribute("location", userDTO.getLocation());
        model.addAttribute("joindate", userDTO.getJoindate());
        return "/view/user/mypage";
    }

}
