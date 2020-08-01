package ksy.geshi.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");
    private String value;

    Role(String value) {
        this.value = value;
    }
}
