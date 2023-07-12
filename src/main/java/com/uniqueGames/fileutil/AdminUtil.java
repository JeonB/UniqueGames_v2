//package com.uniqueGames.fileutil;
//
//import com.uniqueGames.model.Notice;
//import com.uniqueGames.repository.CommentMapper;
//import com.uniqueGames.repository.MemberMapper;
//import com.uniqueGames.repository.NoticeMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//@Component
//public class AdminUtil {
//
//	@Value("${upload-directory}")
//	private String root_path;
//	MemberMapper memberMapper;
//
//	@Autowired
//	private AdminUtil(MemberMapper memberMapper) {
//		this.memberMapper = memberMapper;
//	}
//
//	/**
//	 * pagin utils
//	 *
//	 * @param page
//	 * @return Map<String, Integer>
//	 */
//	public Map<String, Integer> getPagination(String page, String keyword, String type) {
//		Map<String, Integer> result = new HashMap();
//
//		int startCount = 0;
//		int endCount = 0;
//		int pageSize = 8; // 한페이지당 게시물 수
//		int reqPage = 1; // 요청페이지
//		int pageCount = 1; // 전체 페이지 수
//		int dbCount = 0; // DB에서 가져온 전체 행수
//
//		if(){
//
//		}
//
//		if (keyword.equals("list")&&type.equals("member")) { // 검색 키워드가 없는 전체 리스트
//			dbCount = memberMapper.totRowCount();
//
//		} else if(type.equals("member")){
//			dbCount = memberMapper.totRowCountSearch(keyword);
//
//		}
////		else if(keyword.equals("list")&&type.equals("company")){
////			dbCount = companyMapper.totRowCount();
////
////		}else{
////			dbCount = companyMapper.totRowCountSearch(keyword);
////		}
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
//		result.put("startCount", startCount);
//		result.put("endCount", endCount);
//		result.put("pageSize", pageSize);
//		result.put("reqPage", reqPage);
//		result.put("pageCount", pageCount);
//		result.put("dbCount", dbCount);
//
//		return result;
//	}
//
//	/**
//	 * 파일 체크
//	 *
//	 * @param request
//	 * @param notice
//	 * @return
//	 * @throws Exception
//	 */
//	public  Notice fileUtil(HttpServletRequest request, Notice notice) throws Exception {
//
//		if (notice.getFile() != null && !notice.getFile().isEmpty()) {
//
//			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//			String upload_file = notice.getFile().getOriginalFilename();
//			String image_id = uuid + upload_file;
//
//			notice.setUploadFile(upload_file);
//			notice.setImageId(image_id);
//
//		}
//
//		return notice;
//	}
//
//	/**
//	 * 파일 저장
//	 *
//	 * @param notice
//	 * @throws Exception
//	 */
//	public  void fileSaveUtil(Notice notice) throws Exception {
//
//		if (notice.getFile() != null && !notice.getFile().isEmpty()) {
//			File saveFile = new File(root_path + notice.getImageId());
//			notice.getFile().transferTo(saveFile);
//
//		}
//
//	}
//
//	/**
//	 * 파일 업데이트
//	 * @param notice
//	 * @throws Exception
//	 */
//	public void fileUpdateUtil(Notice notice, String oldFileName) throws Exception {
//		String stat = "";
//
//		if (oldFileName.indexOf("!") > -1) {
//			String[] tmp = oldFileName.split("!");
//			stat = tmp[0];
//			oldFileName = tmp[1];
//		}
//
//		if (notice.getFile() != null && !notice.getFile().isEmpty()) {
//			File saveFile = new File(root_path + notice.getImageId());
//			notice.getFile().transferTo(saveFile);
//
//		}
//
//		if (!notice.getFile().isEmpty() || stat.equals("delete")) {
//			File deleteFile = new File(root_path + oldFileName);
//
//			if (deleteFile.exists()) {
//				deleteFile.delete();
//
//			}
//		}
//	}
//
//	/**
//	 * 파일 삭제
//	 * @param imgdel
//	 */
//	public void fileDeleteUtil(String imgdel) {
//
//		if (imgdel != null && !imgdel.equals("")) {
//			File deleteFile = new File(root_path + imgdel);
//			if (deleteFile.exists()) {
//				deleteFile.delete();
//
//			}
//
//		}
//	}
//
//}
