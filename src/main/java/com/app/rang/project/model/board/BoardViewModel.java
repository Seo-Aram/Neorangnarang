package com.app.rang.project.model.board;

import com.app.rang.project.entity.Board;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardViewModel {
    private long boardidx;

    private long useridx;

    private String title;

    private String content;

    private int category;

    private String categoryStr;

    private List<String> img;

    private String thumbnail;

    private int price;

    private String writedate;

    private int onsale;
}
