package com.uniqueGames.service;

import com.uniqueGames.model.Comment;
import com.uniqueGames.model.Member;
import com.uniqueGames.repository.CommentMapper;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentService {

    CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    /**
     * 댓글 작성
     *
     * @param comment 폼 데이터
     * @return FAIL OR SUCCESS
     */
    public String commentInsert(Comment comment) {
        String result = "FAIL";

        comment.setCommentContent(comment.getCommentContent().replaceAll("\r\n", "<br>"));
        int dbResult = commentMapper.insertComment(comment);
        if (dbResult == 1) {
            result = "SUCCESS";
        }

        return result;
    }

    /**
     * 페이지 별 리스트
     *
     * @param no 페이지 번호
     * @return 댓글 리스트
     */
    public List<Comment> select(String no) {

        return commentMapper.selectComment(no);
    }

    /**
     * 댓글 전체 리스트
     *
     * @return List<Comment>
     */
    public List<Comment> selectAll(int startCount, int endCount) {

        return commentMapper.selectAll(startCount, endCount);
    }

    /**
     * id로 댓글 조회
     *
     * @param no 댓글 번호
     * @return Comment
     */
    public Comment selectOne(int no) {

        return commentMapper.selectOneComment(no);
    }

    /**
     * 댓글 삭제
     *
     * @param no 댓글 번호
     * @return FAIL OR SUCCESS
     */
    public String delete(String no) {
        String result = "FAIL";
        int dbResult = commentMapper.deleteComment(no);

        if (dbResult == 1) {
            result = "SUCCESS";
        }

        return result;
    }

    /**
     * 신고 처리
     *
     * @param member  로그인 한 유저
     * @param comment 신고할 댓글 정보
     */
    public void report(Comment comment, Member member) {
        commentMapper.report(comment, member);
    }

    /**
     * 이미 신고한 계정인지 확인
     *
     * @param commentId 댓글 번호
     * @param member    로그인 한 유저 정보
     */
    public boolean isReported(int commentId, Member member) {
        boolean flag = false;

        Comment comment = selectOne(commentId);
        if (comment.getReportedUsers() != null) {
            String[] idCheck = comment.getReportedUsers().split(",");

            for (String id : idCheck) {
                if (id.equals(String.valueOf(member.getId()))) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    /**
     * admin page용
     *
     * @return reportCount 가 담긴 List<Comment>
     */
    public List<Comment> selectAdmin(int startCount, int endCount) {
        List<Comment> list = selectAll(startCount, endCount);

        for (Comment comment : list) {
            if (comment.getReportedUsers() != null) {
                String[] rptArr = comment.getReportedUsers().split(",");
                comment.setReportCount(rptArr.length);
            }
        }

        return list;
    }


    public String reportCancel(int id) {
        int dbResult = commentMapper.reportCancel(id);

        if (dbResult == 1) {

            return "SUCCESS";
        } else {

            return "FAIL";
        }
    }

    public int totRowCount() {
        return commentMapper.totRowCount();
    }
}
