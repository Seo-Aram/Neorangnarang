package com.app.rang.project.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardListModel {
    private Long boardidx;
    private String title;
    private String thumbnail;
    private Integer onsale;
    private Integer price;
    private String writedate;
}
