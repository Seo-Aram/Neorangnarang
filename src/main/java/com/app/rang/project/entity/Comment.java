package com.app.rang.project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="boardcomment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long commentidx;

    @Column
    private Long boardidx;
    @Column
    private Long useridx;

    @Column
    private String nickname;
    @Column
    private  String content;
    @Column
    private LocalDate writedate;

}
