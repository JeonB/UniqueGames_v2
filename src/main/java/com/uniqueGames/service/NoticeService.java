package com.uniqueGames.service;


import com.uniqueGames.model.Notice;

import java.util.ArrayList;

public interface NoticeService {
	
	ArrayList<Notice> getNoticeList(int startCount, int endCount);
	
	Notice getNoticeContent(String stat, String no);
	
	int insert(Notice notice);
	
	int update(Notice notice);
	
	int delete(String no);
	
	int deleteList(String[] list);
	
	Object search(String keyWord, int startCount, int endCount);
	
}
