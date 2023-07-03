package com.uniqueGames.fileutil;

import com.uniqueGames.model.Notice;
import com.uniqueGames.repository.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

@Component
public class BoardUtil {

	private String root_path;
	private String attach_path;
	NoticeDao noticeDao;

	@Autowired
	public BoardUtil(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	/**
	 * 페이징 처리 유틸
	 * 
	 * @param page
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> getPagination(String page, String keyword) {

		Map<String, Integer> result = new HashMap<String, Integer>();

		int startCount = 0;
		int endCount = 0;
		int pageSize = 10; // 한페이지당 게시물 수
		int reqPage = 1; // 요청페이지
		int pageCount = 1; // 전체 페이지 수
		int dbCount = 0; // DB에서 가져온 전체 행수
//
//		if (keyword.equals("list")) { // 검색 키워드가 없는 전체 리스트
//			dbCount = noticeDao.totRowCount("list");
//
//		} else {
//			dbCount = noticeDao.totRowCount(keyword);
//
//		}
//
//		// 총 페이지 수 계산
//		if (dbCount % pageSize == 0) {
//			pageCount = dbCount / pageSize;
//		} else {
//			pageCount = dbCount / pageSize + 1;
//		}
//
//		// 요청 페이지 계산
//		if (page != null) {
//			reqPage = Integer.parseInt(page);
//			startCount = (reqPage - 1) * pageSize + 1;
//			endCount = reqPage * pageSize;
//		} else {
//			startCount = 1;
//			endCount = pageSize;
//		}
//
		result.put("startCount", startCount);
		result.put("endCount", endCount);
		result.put("pageSize", pageSize);
		result.put("reqPage", reqPage);
		result.put("pageCount", pageCount);
		result.put("dbCount", dbCount);

		return result;
	}

	/**
	 * 리스트 날짜, 제목 출력 방식 변경
	 * 
	 * @param result
	 * @return
	 */
	public List<Notice> getOutput(List<Notice> result) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM.dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		String date_output = "";

		LocalDateTime now = LocalDateTime.now();
		int currentYear = now.getYear();
		int currentMonth = now.getMonthValue();
		int currentDay = now.getDayOfMonth();

		for (Notice nvo : result) {
//			LocalDateTime dbDateTime = nvo.getNotice_date().toInstant().atZone(ZoneId.systemDefault())
//					.toLocalDateTime();
//			int dbYear = dbDateTime.getYear();
//			int dbMonth = dbDateTime.getMonthValue();
//			int dbDay = dbDateTime.getDayOfMonth();
//
//			if (dbYear == currentYear && dbMonth == currentMonth && dbDay == currentDay) {
//				// 날짜가 현재 날짜와 일치하는 경우, 시간만 출력
//				String formattedTime = dbDateTime.format(timeFormatter);
//				date_output = formattedTime;
//			} else {
//				// 날짜가 현재 날짜와 일치하지 않는 경우, 날짜만 출력
//				String formattedDate = dbDateTime.format(dateFormatter);
//				date_output = formattedDate;
//			}
//			// date_output 변수를 사용하여 필요한 작업 수행
//			nvo.setDate_output(date_output);
//			nvo.setCmtCount(noticeDao.getCmtCount(nvo.getPost_id()));
		}

		return result;
	}

	/**
	 * 파일 체크
	 * 
	 * @param request
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	public  Notice fileUtil(HttpServletRequest request, Notice notice) throws Exception {
		root_path = request.getSession().getServletContext().getRealPath("/");
		attach_path = "\\resources\\upload\\";

		if (notice.getFile() != null && !notice.getFile().isEmpty()) {

			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String upload_file = notice.getFile().getOriginalFilename();
			String image_id = uuid + upload_file;

			notice.setUpload_file(upload_file);
			notice.setImage_id(image_id);

		}

		return notice;
	}

	/**
	 * 파일 저장
	 * 
	 * @param notice
	 * @throws Exception
	 */
	public  void fileSaveUtil(Notice notice) throws Exception {

		if (notice.getFile() != null && !notice.getFile().isEmpty()) {
			File saveFile = new File(root_path + attach_path + notice.getImage_id());
			notice.getFile().transferTo(saveFile);

		}

	}

	/**
	 * 파일 업데이트
	 * @param notice
	 * @throws Exception
	 */
	public void fileUpdateUtil(Notice notice, String oldFileName) throws Exception {
		String stat = "";

		if (oldFileName.indexOf("!") > -1) {
			String[] tmp = oldFileName.split("!");
			stat = tmp[0];
			oldFileName = tmp[1];
		}
		
		if (notice.getFile() != null && !notice.getFile().isEmpty()) {
			File saveFile = new File(root_path + attach_path + notice.getImage_id());
			notice.getFile().transferTo(saveFile);

		}

		if (!notice.getFile().isEmpty() || stat.equals("delete")) {
			File deleteFile = new File(root_path + attach_path + oldFileName);

			if (deleteFile.exists()) {
				deleteFile.delete();

			}
		}
	}
	
	/**
	 * 파일 삭제
	 * @param imgdel
	 */
	public void fileDeleteUtil(String imgdel) {

		if (imgdel != null && !imgdel.equals("")) {
			File deleteFile = new File(root_path + attach_path + imgdel);
			if (deleteFile.exists()) {
				deleteFile.delete();

			}

		}
	}

}
