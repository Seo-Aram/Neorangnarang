package com.app.rang.project.model.comment;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.util.Util;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentListModel {

    private Long commentidx;
    private Long boardidx;
    private Long useridx;

    private String nickname;
    private  String content;
    private String writedate;

    private boolean ismine;

}
