package ksy.geshi.repository;

import ksy.geshi.domain.BoardEntity;
import ksy.geshi.form.BoardSearch;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardEntity> searchAll(BoardSearch boardSearch, int page);
}
