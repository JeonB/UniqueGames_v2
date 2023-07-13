package com.uniqueGames.service;


import com.uniqueGames.model.Notice;

import java.util.List;

public interface NoticeService {
	
	List<Notice> getNoticeList(int startCount, int endCount);
	
	Notice getNoticeContent(String stat, String no);
	
	int insert(Notice notice);
	
	int update(Notice notice);
	
	int delete(String no, String imgDel);
	
	int deleteList(String[] list);
	
	Object search(String keyWord, int startCount, int endCount);
	
}
