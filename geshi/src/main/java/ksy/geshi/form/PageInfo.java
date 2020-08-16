package ksy.geshi.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
    //최소 페이지 번호
    private int min;
    //최대 페이지 번호
    private int max;
    //이전 버튼의 페이지 번호
    private int prevPage;
    //다음 버튼의 페이지 번호
    private int nextPage;
    //전체 페이지 개수
    private int pageCnt;
    //현재 페이지 개수
    private int currentPage;

    //contentCnt:전체 글 개수, currentPage: 현재 페이지 번호, contentPageCnt: 페이지 당 글의 개수, paginationCnt: 페이지 버튼의 개수
    public PageInfo(int contentCnt, int currentPage,int contentPageCnt, int paginationCnt) {
        this.currentPage=currentPage;
        pageCnt=contentCnt/contentPageCnt;
        if(contentCnt%contentPageCnt>0) pageCnt++;
        min=((currentPage-1)/paginationCnt)*paginationCnt+1;
        max=min+paginationCnt-1;
        if(max>pageCnt) max=pageCnt;
        prevPage=min-1;
        nextPage=max+1;
        if(nextPage>pageCnt) nextPage=pageCnt;

    }
}
