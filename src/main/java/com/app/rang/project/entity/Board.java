package com.app.rang.project.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicInsert
@DynamicUpdate
@Table(name="board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long boardidx;

    @Column(columnDefinition = "varchar(256) not null")
    private String title;
    @Column(columnDefinition = "text not null")
    private String content;
    @Column(columnDefinition = "integer not null")
    private Integer category;
    @Column
    private String thumbnail;
    @Column(columnDefinition = "varchar(1000) not null")
    private String img;
    @Column(columnDefinition = "integer not null default 1")
    private Integer onsale;
    @Column(columnDefinition = "integer not null")
    private Integer price;
    @Column(columnDefinition = "bigint not null default 0")
    private Long useridx;
    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate writedate;

}
