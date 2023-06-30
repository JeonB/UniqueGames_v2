//package com.team2.uniquegames_v2.dao;
//
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//
//@Repository
//public class NoticeDao {
//
//    @Autowired
//    private SqlSessionTemplate sqlSessionTemplate;
//    /**
//     * totRowCount - 페이징 처리용 전체 컬럼 수
//     */
//    public int totRowCount(String keyword) {
//        int result = 0;
//        String sql;
//        if (keyword.equals("list")) {
//            sql = "SELECT COUNT(*) FROM NOTICE";
//        } else {
//            sql = "SELECT COUNT(*) FROM NOTICE WHERE TITLE LIKE CONCAT('%', #{keyword}, '%')";
//        }
//
//        try {
//            if (!keyword.equals("list")) {
//                result = sqlSessionTemplate.selectOne(sql, keyword);
//            } else {
//                result = sqlSessionTemplate.selectOne(sql);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    /**
//     * 댓글 개수 표시용
//     */
//    public int getCmtCount(int no) {
//        int result = 0;
//        String sql = "SELECT COUNT(*) FROM COMMENT WHERE POST_ID = #{no}";
//
//        try {
//            result = sqlSessionTemplate.selectOne(sql, no);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//}
