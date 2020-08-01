package ksy.geshi.service;

import ksy.geshi.domain.BoardEntity;
import ksy.geshi.form.BoardForm;
import ksy.geshi.form.BoardSearch;
import ksy.geshi.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardForm> getBoardList(BoardSearch boardSearch, int page) {
        List<BoardEntity> all = boardRepository.searchAll(boardSearch,page);
        return all.stream()
                .map(o -> new BoardForm(o))
                .collect(Collectors.toList());
    }

    public Long boardRegister(BoardForm boardForm,String userId) {
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.valueSetting(boardForm,userId);
        Long board_idx=boardRepository.save(boardEntity).getBoard_idx();;

        return board_idx;
    }

    public BoardEntity getContentInfo(Long board_idx) {
        Optional<BoardEntity> findContent = boardRepository.findById(board_idx);
        if(findContent.isPresent()==true){
            return findContent.get();
        }else{
            return null;
        }
    }

    public void delete(Long board_idx) {
        boardRepository.deleteById(board_idx);
    }

    public Long updateBoard(BoardForm boardForm) {
        BoardEntity boardEntity = boardRepository.findById(boardForm.getBoard_idx()).get();
        boardEntity.changeTitleAndContent(boardForm);
        return boardEntity.getBoard_idx();
    }

    public int getBoardCount() {
        List<BoardEntity> result = boardRepository.findAll();
        return result.size();
    }
}
