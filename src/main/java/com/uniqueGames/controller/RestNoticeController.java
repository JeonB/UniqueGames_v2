package com.uniqueGames.controller;

import com.uniqueGames.config.Login;
 import com.uniqueGames.fileutil.FileUploadUtil;
import com.uniqueGames.model.Comment;
import com.uniqueGames.model.Company;
import com.uniqueGames.service.CommentService;
import com.uniqueGames.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestNoticeController extends FileUploadUtil {

    private final CommentService commentService;
    private final NoticeService noticeService;

    @Override
    protected void extractFile(Object obj) {
        setFile((MultipartFile) obj);
    }

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

    @PostMapping("/test")
    public Map<String, Object> test(@RequestParam Map<String, Object> paramMap, MultipartRequest request) {
        String fileName = fileCheck(request.getFile("upload"));
        fileSave();
        log.info(getFile().toString());
        log.info(getImageName());
        paramMap.put("url", "/upload/" + getImageName());
        return paramMap;
    }
}
