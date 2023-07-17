package com.uniqueGames.service;


import com.uniqueGames.model.Company;
import com.uniqueGames.model.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
	
	List<Notice> getNoticeList(int startCount, int endCount);
	
	Notice getNoticeContent(String stat, String no);
	
	int insert(Notice notice);
	
	int update(Notice notice);
	
	int delete(String no, String imgDel);
	
	String deleteList(String[] list, Company company);

	Object search(String keyWord, Map pageMap, String searchType);
	
}
