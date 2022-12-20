package com.app.rang.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long boardidx;

    @Column
    private String title;
    @Column
    private String content;
    @Column
    private Integer category;
    @Column
    private String thumbnail;
    @Column
    private String img;
    @Column
    private Integer onsale;
    @Column
    private Integer price;
    @Column
    private Integer useridx;
    @Column
    private LocalDate writedate;

}
