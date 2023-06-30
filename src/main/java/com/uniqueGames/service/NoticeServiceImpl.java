package com.uniqueGames.service;


import com.uniqueGames.fileutil.BoardUtil;
import com.uniqueGames.model.NoticeVo;
import com.uniqueGames.repository.NoticeMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	/**
	 * 공지사항 - 전체 리스트 조회
	 */
	@Override
	public ArrayList<NoticeVo> getNoticeList(int startCount, int endCount) {
		return (ArrayList<NoticeVo>) BoardUtil.getOutput(noticeMapper.selectNotice(startCount, endCount));
	}

	/**
	 * 공지사항 - 상세 보기
	 */
	@Override
	public NoticeVo getNoticeContent(String stat, String no) {
		NoticeVo noticeVo = noticeMapper.selectContent(no);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (noticeVo != null) {
			if (stat == null || stat.equals("")) {
				noticeMapper.hitsCount(no);
				noticeVo.setNotice_hits(noticeVo.getNotice_hits() + 1);
			}

			noticeVo.setDate_output(format.format(noticeVo.getNotice_date()));

		}

		return noticeVo;
	}

	/**
	 * 공지사항 - 작성
	 * 
	 * @throws Exception
	 */
	@Override
	public int insert(NoticeVo noticeVo) {

		int insResult = noticeMapper.insertNotice(noticeVo);
		if (noticeVo.getImage_id() != null) {
			noticeMapper.insertFile(noticeVo);
		}

		return insResult;
	}

	/**
	 * 공지사항 - 수정
	 */
	@Override
	public int update(NoticeVo noticeVo) {
		int result = 0;

		result = noticeMapper.updateNotice(noticeVo);

		if (noticeVo.getFile() != null && !noticeVo.getFile().isEmpty()) {

			if (noticeMapper.fileCheck(noticeVo) == 1) {
				noticeMapper.updateFile(noticeVo);

			} else {
				noticeMapper.updateUploadFile(noticeVo);

			}

		} else if (noticeVo.getImage_id() != null && noticeVo.getImage_id().split("!")[0].equals("delete")) {

			noticeMapper.deleteFile(noticeVo);
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
	public List<NoticeVo> search(String keyword, int startCount, int endCount) {

		return BoardUtil.getOutput(noticeMapper.searchList(keyword, startCount, endCount));
	}

}
