package com.uniqueGames.service;


import com.uniqueGames.fileutil.BoardUtil;
import com.uniqueGames.model.Notice;
import com.uniqueGames.repository.NoticeMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.uniqueGames.test.GenericImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends GenericImpl2 implements NoticeService {

	private NoticeMapper noticeMapper;
	private BoardUtil boardUtil;

	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper, BoardUtil boardUtil) {
		this.noticeMapper = noticeMapper;
		this.boardUtil = boardUtil;
	}

	@Override
	protected void extractFile(Object obj) {
		if (obj.toString().contains("Notice")) {
			Notice notice = (Notice) obj;
			super.setFile(notice.getFile());
		}

	}

	/**
	 * 공지사항 - 전체 리스트 조회
	 */
	@Override
	public List<Notice> getNoticeList(int startCount, int endCount) {
		return boardUtil.getOutput(noticeMapper.selectNotice(startCount, endCount));
//		return noticeMapper.selectNotice(1, 10);
	}

	/**
	 * 공지사항 - 상세 보기
	 */
	@Override
	public Notice getNoticeContent(String stat, String no) {
		Notice notice = noticeMapper.selectContent(no);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (notice != null) {
			if (stat == null || stat.equals("")) {
				noticeMapper.hitsCount(no);
				notice.setNoticeHits(notice.getNoticeHits() + 1);
			}

			notice.setDateOutput(format.format(notice.getNoticeDate()));

		}

		return notice;
	}

	/**
	 * 공지사항 작성
	 * @param notice
	 * @return
	 */
	@Override
	public int insert(Notice notice) {
		notice.setImageId(super.fileCheck(notice));
		int insResult = noticeMapper.insertNotice(notice);
		if (notice.getImageId() != null) {
			noticeMapper.insertFile(notice);
			super.fileSaveUtil();
		}

		return insResult;
	}

	/**
	 * 공지사항 - 수정
	 */
	@Override
	public int update(Notice notice) {
		String oldFileName = notice.getImageId();

//		notice.setImageId(super.fileCheck(notice));
//		super.fileUpdateUtil(notice.getImageId());
		System.out.println(notice.getImageId());

		int result = noticeMapper.updateNotice(notice);
		if (notice.getFile() != null && !notice.getFile().isEmpty()) {

			if (noticeMapper.fileCheck(notice) == 1) {
				noticeMapper.updateFile(notice);

			} else {
				noticeMapper.updateUploadFile(notice);

			}

		} else if (notice.getImageId() != null && notice.getImageId().split("!")[0].equals("delete")) {

			noticeMapper.deleteFile(notice);
		}

		return result;
	}

	/**
	 * 공지사항 - 삭제
	 */
	@Override
	public int delete(String no) {

		return noticeMapper.deleteNotice(no);
	}

	@Override
	public int deleteList(String[] list) {

		return noticeMapper.deleteList(list);
	}

	/**
	 * 공지사항 - 검색
	 */
	@Override
	public List<Notice> search(String keyword, int startCount, int endCount) {

		return boardUtil.getOutput(noticeMapper.searchList(keyword, startCount, endCount));
	}

}
