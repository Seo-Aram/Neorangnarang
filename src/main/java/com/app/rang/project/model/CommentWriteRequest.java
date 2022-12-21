package com.app.rang.project.model;

import com.app.rang.project.entity.Comment;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentWriteRequest {


    private Long commentidx;
    private Long boardidx;
    private Long useridx;
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
