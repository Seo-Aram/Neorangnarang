package com.app.rang.project.model.comment;

import com.app.rang.project.entity.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentWriteRequest {

    private long boardidx;
    private long useridx;
    private String nickname;
    private  String content;


    public Comment toComment(){
        return Comment.builder()
                .boardidx(boardidx)
                .useridx(useridx)
                .nickname(nickname)
                .content(content)
                .build();
    }

}
