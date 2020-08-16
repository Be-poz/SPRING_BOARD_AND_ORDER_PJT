package ksy.geshi.form;

import ksy.geshi.domain.BoardEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardForm {

    private Long board_idx;

    @NotBlank(message = "제목을 입력해주세요")
    @Size(max=15, message = "최대 15글자 까지만 쓸 수 있습니다")
    private String title;

    private String writer;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    private LocalDateTime time;

    public BoardForm(BoardEntity boardEntity) {
        this.board_idx=boardEntity.getBoard_idx();
        this.title=boardEntity.getTitle();
        this.writer=boardEntity.getWriter();
        this.content=boardEntity.getContent();
        this.time=boardEntity.getCreatedDate();
    }
}
