package com.uniqueGames.service;


import com.uniqueGames.model.NoticeVo;
import java.util.ArrayList;

public interface NoticeService {
	
	ArrayList<NoticeVo> getNoticeList(int startCount, int endCount);
	
	NoticeVo getNoticeContent(String stat, String no);
	
	int insert(NoticeVo noticeVo);
	
	int update(NoticeVo noticeVo);
	
	int delete(String no);
	
	int deleteList(String[] list);
	
	Object search(String keyWord, int startCount, int endCount);
	
}
