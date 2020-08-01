package ksy.geshi.domain;

import ksy.geshi.form.BoardForm;
import lombok.Generated;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "board")
public class BoardEntity extends BaseEntity{

    @Id @GeneratedValue
    @Column(name="board_Id")
    private Long board_idx;

    private String title;

    private String writer;

    private String content;

    public void valueSetting(BoardForm boardForm,String userId) {
        this.title=boardForm.getTitle();
        this.writer=userId;
        this.content=boardForm.getContent();
    }

    public void changeTitleAndContent(BoardForm boardForm) {
        this.title=boardForm.getTitle();
        this.content=boardForm.getContent();
    }
}
