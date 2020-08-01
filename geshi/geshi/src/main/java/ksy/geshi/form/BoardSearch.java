package ksy.geshi.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSearch {
    private SearchCondition searchCondition;
    private String inputValue;
    private int page;
}
