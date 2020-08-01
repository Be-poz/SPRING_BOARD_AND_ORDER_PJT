package ksy.geshi.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ksy.geshi.domain.BoardEntity;
import ksy.geshi.domain.QBoardEntity;
import ksy.geshi.form.BoardSearch;

import javax.persistence.EntityManager;
import java.util.List;

import static ksy.geshi.domain.QBoardEntity.boardEntity;
import static org.springframework.util.StringUtils.hasText;
import static org.springframework.util.StringUtils.isEmpty;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public BoardRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<BoardEntity> searchAll(BoardSearch boardSearch, int page) {
        return queryFactory
                .selectFrom(boardEntity)
                .where(
                        optionEq(boardSearch)
                )
                .offset((page-1)*2)
                .limit(2)
                .orderBy(boardEntity.board_idx.desc())
                .fetch();
    }

    private BooleanExpression optionEq(BoardSearch boardSearch) {
        if(!hasText(boardSearch.getInputValue())||isEmpty(boardSearch.getSearchCondition())) return null;
        else {
            return boardSearch.getSearchCondition().toString().equals("제목")
                    ? boardEntity.title.eq(boardSearch.getInputValue())
                    : boardEntity.writer.eq(boardSearch.getInputValue());
        }
    }
}
