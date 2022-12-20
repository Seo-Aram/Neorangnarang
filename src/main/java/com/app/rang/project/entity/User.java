package com.app.rang.project.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long useridx;

    @Column
    private String username;
    @Column
    private String password;

    @Column
    private String nickname;
    @Column
    private String nation;
    @Column
    private String phone;

    @Column
    private String location;
    @Column
    private Integer byear;
    @Column
    private Integer bmonth;
    @Column
    private Integer bday;

    @Column
    private LocalDate joindate;
    @Column
    private Boolean deleted;


}
