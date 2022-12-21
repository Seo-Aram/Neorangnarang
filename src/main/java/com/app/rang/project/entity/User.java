package com.app.rang.project.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long useridx;

    @Column(columnDefinition = "varchar(256) unique not null")
    private String username;
    @Column(columnDefinition = "varchar(256) not null")
    private String password;

    @Column(columnDefinition = "varchar(20) not null")
    private String nickname;
    @Column(columnDefinition = "varchar(100)")
    private String nation;
    @Column(columnDefinition = "varchar(12) not null")
    private String phone;

    @Column(columnDefinition = "varchar(100) not null")
    private String location;
    @Column
    private Integer byear;
    @Column
    private Integer bmonth;
    @Column
    private Integer bday;

    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate joindate;
    @Column(columnDefinition = "tinyint default 0")
    private Boolean deleted;


}
