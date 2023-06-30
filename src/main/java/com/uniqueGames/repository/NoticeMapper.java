package com.uniqueGames.repository;


import com.uniqueGames.model.CommentVo;
import com.uniqueGames.model.NoticeVo;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NoticeMapper {
	ArrayList<NoticeVo> selectNotice(@Param("start") int startCount, @Param("end") int endCount);

	NoticeVo selectContent(String no);

	int insertNotice(NoticeVo noticeVo);

	int updateNotice(NoticeVo noticeVo);

	int deleteNotice(String no);

	int deleteList(String[] list);

	List<NoticeVo> searchList(@Param("keyword") String keyword, @Param("start") int startCount,
			@Param("end") int endCount);

	void hitsCount(String no);

	int insertFile(NoticeVo noticeVo);

	int updateFile(NoticeVo noticeVo);

	int updateUploadFile(NoticeVo noticeVo);

	int deleteFile(NoticeVo noticeVo);

	int fileCheck(NoticeVo noticeVo);

	ArrayList<CommentVo> selectComment(String no);

	int insertComment(CommentVo commentVo);

	int deleteComment(String no);
}
