package com.app.rang.project.entity;

import com.app.rang.project.model.CommentListModel;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
@Table(name="boardcomment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long commentidx;

    /*@Column
    @ManyToOne
    @JoinColumn(name = "boardidx")
    private Board boardidx;

    @Column
    @ManyToOne
    @JoinColumn(name = "useridx")
    private User useridx;*/

    @Column(columnDefinition = "bigint not null")
    private Long boardidx;
    @Column(columnDefinition = "bigint not null default 0")
    private Long useridx;

    @Column
    private String nickname;
    @Column(columnDefinition = "text not null")
    private  String content;
    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate writedate;

    public CommentListModel toCommentView(){
        return CommentListModel.builder()
                .commentidx(commentidx)
                .boardidx(boardidx)
                .useridx(useridx)
                .nickname(nickname)
                .content(content)
                .writedate(writedate.toString())
                .build();
    }

}
