package com.uniqueGames.controller;

import com.uniqueGames.model.Comment;
import com.uniqueGames.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RestNoticeController {

    private final CommentService commentService;

    /**
     * 댓글 작성 처리
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
}
