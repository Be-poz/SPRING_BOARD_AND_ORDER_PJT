package ksy.geshi.controller;

import ksy.geshi.domain.BoardEntity;
import ksy.geshi.form.BoardForm;
import ksy.geshi.form.BoardSearch;
import ksy.geshi.form.PageInfo;
import ksy.geshi.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;


    @GetMapping("/main")
    public String main(@ModelAttribute("boardSearch") BoardSearch boardSearch, @RequestParam("page")int page, Model model){
        List<BoardForm> list=boardService.getBoardList(boardSearch,page);
        int totalCnt = boardService.getBoardCount();
        PageInfo pageInfo=new PageInfo(totalCnt,page,2,10);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("page",page);
//        log.info(
//                "min: "+pageInfo.getMin()
//                +"\nmax: "+pageInfo.getMax()
//                +"\nprevPage: "+pageInfo.getPrevPage()
//                +"\nnextPage: "+pageInfo.getNextPage()
//                +"\npageCnt: "+pageInfo.getPageCnt()
//                +"\ncurrentPage: "+pageInfo.getCurrentPage()
//        );
        return "board/board_main";
    }

    @GetMapping("/write")
    public String write(@ModelAttribute("boardForm")BoardForm boardForm) {
        return "board/board_write";
    }

    @PostMapping("/write")
    public String write_pro(@Valid @ModelAttribute("boardForm") BoardForm boardForm, BindingResult result,Model model,HttpServletRequest request) {
        HttpSession session=request.getSession();
        String userId=(String)session.getAttribute("userId");
        if (result.hasErrors()) {
            return "board/board_write";
        } else {
            Long board_idx=boardService.boardRegister(boardForm,userId);
            model.addAttribute("board_idx",board_idx);
            return "board/write_success";
        }
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("board_idx") Long board_idx, Model model,HttpServletRequest request) {
        HttpSession session=request.getSession();
        String userId=(String)session.getAttribute("userId");
        model.addAttribute("board_idx",board_idx);
        BoardEntity boardEntity = boardService.getContentInfo(board_idx);

        if(!boardEntity.getWriter().equals(userId)){
            return "user_denied";
        }else{
            BoardForm boardForm=new BoardForm(boardEntity);
            model.addAttribute("boardForm",boardForm);
            return "board/board_modify";
        }

    }

    @PostMapping("/modify")
    public String modify_pro(@Valid @ModelAttribute("boardForm")BoardForm boardForm,BindingResult result,Model model){
        if (result.hasErrors()) {
            return "board/modify";
        } else {
            Long id = boardService.updateBoard(boardForm);
            model.addAttribute("board_idx",id);
            return "board/modify_success";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("board_idx") Long board_idx,HttpServletRequest request){
        HttpSession session=request.getSession();
        String userId=(String)session.getAttribute("userId");
        BoardEntity boardEntity = boardService.getContentInfo(board_idx);

        if(!boardEntity.getWriter().equals(userId)){
            return "user_denied";
        }else{
            boardService.delete(board_idx);
            return "board/delete_success";
        }

    }

    @GetMapping("/read")
    public String read(@RequestParam("board_idx") Long board_idx, Model model) {
        model.addAttribute("board_idx",board_idx);
        BoardEntity boardEntity = boardService.getContentInfo(board_idx);
        BoardForm boardForm=new BoardForm(boardEntity);
        model.addAttribute("boardForm",boardForm);
        return "board/board_read";
    }

}
