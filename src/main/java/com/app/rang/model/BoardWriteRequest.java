package com.app.rang.model;

import com.app.rang.entity.Board;
import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardWriteRequest {

    private String title;

    private String content;

    private Integer category;

    private String thumbnail;
    private String img;

    private Integer price;


    public Board toBoardEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .price(price)
                .build();
    }

}
