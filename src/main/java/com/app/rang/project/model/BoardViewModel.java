package com.app.rang.project.model;

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

    private String title;

    private String content;

    private String category;

    private List<String> img;

    private int price;

    private String writedate;

    private int onsale;
}
