package com.uniqueGames.controller;

import com.uniqueGames.config.Login;
import com.uniqueGames.fileutil.FileUploadUtil;
import com.uniqueGames.model.Comment;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.service.AwsS3Service;
import com.uniqueGames.service.CommentService;
import com.uniqueGames.service.MailSendService;
import com.uniqueGames.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RestNoticeController {

    // @RequiredArgsConstructor 적용
    private final CommentService commentService;
    private final NoticeService noticeService;
    private final MailSendService mailSendService;
    private final AwsS3Service awsS3Service;


    /**
     * 댓글 작성 처리
     *
     * @param comment 폼 데이터
     * @return FAIL OR SUCCESS
     */
    @PostMapping("/commentWriteProc")
    public String commentWriteProc(Comment comment) {
        return commentService.commentInsert(comment);
    }

    /**
     * 댓글 삭제 처리
     *
     * @param no 댓글 번호
     * @return FAIL OR SUCCESS
     */
    @DeleteMapping("/comment-delete")
    public String commentDelete(@RequestParam("no") String no) {

        return commentService.delete(no);
    }

    /**
     * 공지사항 리스트에서 삭제 처리
     *
     * @param list 공지사항 id를 담은 배열
     * @param company 현재 로그인 한 회사 정보
     * @return FAIL OR SUCCESS
     */
    @DeleteMapping("/board-manage")
    public String boardManage(String[] list, @Login Company company) {


        return noticeService.deleteList(list, company);
    }

    /**
     * 에디터 이미지 업로드
     *
     * @param paramMap 아무것도 받지 않으면 객체 생성
     * @param request  파일
     * @return 이미지 src 위치 경로
     */
    @PostMapping("/imgUpload")
    public Map<String, Object> imgUpload(@RequestParam Map<String, Object> paramMap, MultipartRequest request) throws IOException {
        String url = awsS3Service.uploadFile(request.getFile("upload"));
        paramMap.put("url", url);
        return paramMap;
    }

    /**
     * 작성 취소시 파일 업로드 취소(삭제)
     *
     * @param deleteImgArray 이미지 이름 배열
     */
    @DeleteMapping("/imgDelete")
    public String imgDelete(@RequestParam("deleteImgArray") String[] deleteImgArray) {
        awsS3Service.deleteFile(List.of(deleteImgArray));
        return "GOOD";
    }

    /**
     * 신고 처리
     *
     * @param map    댓글의 id 'id', 신고 사유 'reason' 이 담긴 객체
     * @param member 로그인 한 유저 정보
     * @return Email Send Success or stack trace
     */
    @PutMapping("/reportSend")
    public String reportProc(@RequestBody Map<String, String> map, @Login Member member) {
        Comment comment = commentService.selectOne(Integer.parseInt(map.get("id")));
        comment.setReason(map.get("reason"));
        commentService.report(comment, member);

        return mailSendService.reportEmail(comment);
    }

    /**
     * 어드민이 확인 후 삭제 사유가 합당하지 않을 때 댓글에 대한 신고 취소
     *
     * @param id 댓글 번호
     * @return FAIL OR SUCCESS
     */
    @PutMapping("/reportCancel")
    public String reportCancel(@RequestParam("id") int id) {

        return commentService.reportCancel(id);
    }

    /**
     * 신고 팝업 창 열기
     *
     * @param commentId 댓글 번호
     * @param member    로그인한 사용자
     * @return 이미 신고한 아이디라면 OK, 아니라면 해당 댓글의 정보 반환
     */
    @PostMapping("/popUpInit")
    public Map<String, Object> popUpInit(@RequestParam("commentId") int commentId, @Login Member member) {
        Map<String, Object> map = new HashMap<>();

        if (commentService.isReported(commentId, member)) {
            map.put("cmtResult", "OK");
        } else {
            map.put("cmtResult", commentService.selectOne(commentId));
        }

        return map;
    }
}
