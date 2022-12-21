package com.app.rang.project.model.board;

import com.app.rang.project.entity.Board;
import lombok.*;

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
                .img(img)
                .thumbnail(thumbnail)
                .price(price)
                .build();
    }

}
