package com.uniqueGames.controller;

import com.uniqueGames.config.Login;
import com.uniqueGames.model.Comment;
import com.uniqueGames.model.Company;
import com.uniqueGames.service.CommentService;
import com.uniqueGames.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RestNoticeController {

    private final CommentService commentService;
    private final NoticeService noticeService;

    /**
     * 댓글 작성 처리
     *
     * @param comment
     * @return
     */
    @PostMapping("/commentWriteProc")
    public String commentWriteProc(Comment comment) {
        return commentService.commentInsert(comment);
    }

    /**
     * comment_delete 댓글 삭제 처리
     */
    @DeleteMapping("/comment-delete")
    public String commentDelete(@RequestParam("no") String no) {

        return commentService.delete(no);
    }

    /**
     * board_manage 리스트 선택 삭제 처리
     */
    @DeleteMapping("/board-manage")
    public String boardManage(String[] list, @Login Company company) {


        return noticeService.deleteList(list, company);
    }
}
