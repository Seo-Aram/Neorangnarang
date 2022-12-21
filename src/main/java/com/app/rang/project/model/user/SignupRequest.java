package com.app.rang.project.model.user;

import com.app.rang.project.entity.User;
import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SignupRequest {
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String location;
    private int byear;
    private int bmonth;
    private int bday;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .location(location)
                .byear(byear)
                .bmonth(bmonth)
                .bday(bday)
                .build();
    }
}
