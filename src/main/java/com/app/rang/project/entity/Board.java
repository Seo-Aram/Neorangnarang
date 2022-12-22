package com.app.rang.project.entity;

import com.app.rang.project.model.board.BoardListModel;
import com.app.rang.project.model.board.BoardViewModel;
import com.app.rang.project.util.CategoryUtil;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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

    /*@ManyToOne
    @Column(columnDefinition = "bigint not null default 0")
    @JoinColumn(name = "useridx")
    private User useridx;*/

    @Column(columnDefinition = "bigint not null default 0", updatable = false)
    private Long useridx;

    @Column(columnDefinition = "timestamp not null default current_timestamp()", updatable = false)
    private LocalDate writedate;


    public BoardListModel toBoardView(){
        return BoardListModel.builder()
                .boardidx(boardidx)
                .title(title)
                .thumbnail(thumbnail)
                .onsale(onsale)
                .price(price)
                .writedate(writedate.toString())
                .build();
    }

    public BoardViewModel toBoardViewModel() {
        return BoardViewModel.builder()
                .boardidx(boardidx)
                .useridx(useridx)
                .title(title)
                .content(content)
                .onsale(onsale)
                .price(price)
                .thumbnail(thumbnail)
                .img(new ArrayList<String>(Arrays.asList(img.split(","))))
                .category(category)
                .categoryStr(CategoryUtil.categoryNames.get(category))
                .writedate(writedate.toString())
                .build();
    }
}
