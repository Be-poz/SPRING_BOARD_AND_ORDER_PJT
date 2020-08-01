package ksy.geshi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "member")
public class MemberEntity {
    @Id
    @Column(name="member_Id")
    private String id;

    private String username;
    private String password;

    @Builder
    public void memberSetting(String id, String username, String password) {
        this.id=id;
        this.username=username;
        this.password=password;
    }
}
