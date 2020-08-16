package ksy.geshi.form;

import ksy.geshi.domain.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message ="아이디를 입력해주세요")
    @Pattern(regexp ="[a-zA-Z0-9]*",message = "알파벳과 숫자로만 입력해주세요")
    private String userId;

    @NotEmpty(message ="이름을 입력해주세요")
    @Pattern(regexp="[가-힣]*",message = "한글만 가능합니다")
    @Size(min=2,max=4,message = "2~4글자만 가능합니다")
    private String userName;

    @NotEmpty(message ="비밀번호를 입력해주세요")
    @Pattern(regexp ="[a-zA-Z0-9]*",message = "알파벳과 숫자로만 입력해주세요")
    @Size(min=4,max=20,message = "4~20글자만 가능합니다")
    private String password;

    @NotEmpty(message ="확인 비밀번호를 입력해주세요")
    @Pattern(regexp ="[a-zA-Z0-9]*",message = "알파벳과 숫자로만 입력해주세요")
    @Size(min=4,max=20,message = "4~20글자만 가능합니다")
    private String password2;

    //중복확인 체크를 위한 변수
    private boolean userIdExist;

    public void settings(MemberEntity memberEntity) {
        this.userId=memberEntity.getId();
        this.userName=memberEntity.getUsername();
        this.password=memberEntity.getPassword();
    }
}
