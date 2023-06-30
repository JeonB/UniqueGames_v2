package com.uniqueGames.controller;


import com.uniqueGames.fileutil.BoardUtil;
import com.uniqueGames.model.CommentVo;
import com.uniqueGames.model.CompanyVo;
import com.uniqueGames.model.NoticeVo;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.service.CommentService;
import com.uniqueGames.service.NoticeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({SessionConstants.LOGIN_MEMBER,"list","noticeVo"})
public class NoticeController {

	NoticeService noticeService;
	CommentService commentService;

	@Autowired
	public NoticeController(NoticeService noticeService, CommentService commentService) {
		this.noticeService = noticeService;
		this.commentService = commentService;
	}

	/**
	 * notice-list 공지사항 - 전체 리스트
	 */
	@RequestMapping(value = "/notice_list", method = RequestMethod.GET)
	public ModelAndView noticeList(String page, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView model = new ModelAndView();

		// 페이징 처리 - startCount, endCount 구하기
		Map<String, Integer> pageMap = BoardUtil.getPagination(page, "list");
		ArrayList<NoticeVo> list = noticeService.getNoticeList(pageMap.get("startCount"), pageMap.get("endCount"));
		
		model.addObject("list", list);
		model.addObject("dbCount", pageMap.get("dbCount"));
		model.addObject("pageSize", pageMap.get("pageSize"));
		model.addObject("pageCount", pageMap.get("pageCount"));
		model.addObject("page", pageMap.get("reqPage"));

		model.setViewName("/notice/notice_list");

		return model;
	}

	/**
	 * notice_write 공지사항 - 작성
	 */
	@RequestMapping(value = "/notice_write", method = RequestMethod.GET)
	public String noticeWrite() {
		return "/notice/notice_write";
	}

	/**
	 * notice_write_proc 공지사항 - 작성 처리
	 */
	@RequestMapping(value = "/notice_write_proc", method = RequestMethod.POST)
	public String noticeWriteProc(NoticeVo noticeVo, @ModelAttribute(SessionConstants.LOGIN_MEMBER) CompanyVo cvo,
			HttpServletRequest request, RedirectAttributes attributes) throws Exception {

		noticeVo = BoardUtil.fileUtil(request, noticeVo);
		noticeVo.setCompany_id(cvo.getCompany_id());
		int result = noticeService.insert(noticeVo);

		if (result == 1) {
			BoardUtil.fileSaveUtil(noticeVo);
			attributes.addFlashAttribute("result", "insuccess");

		} else {
			attributes.addFlashAttribute("result", "fail");

		}

		return "redirect:/notice_content?no=" + noticeVo.getPost_id();
	}

	/**
	 * notice_content 공지사항 - 상세 보기
	 */
	@RequestMapping(value = "/notice_content", method = RequestMethod.GET)
	public ModelAndView noticeContent(String stat, String no) {
		ModelAndView model = new ModelAndView();

		NoticeVo noticeVo = noticeService.getNoticeContent(stat, no);
		List<CommentVo> commList = commentService.select(no);

		model.addObject("noticeVo", noticeVo);
		model.addObject("commList", commList);
		model.setViewName("/notice/notice_content");

		return model;
	}

	/**
	 * notice_delete 공지사항 - 삭제
	 */
	@RequestMapping(value = "/notice_delete", method = RequestMethod.POST)
	public String noticeDelete(String no, String imgdel, RedirectAttributes attributes) {

		int result = noticeService.delete(no);
		if (result == 1) {
			BoardUtil.fileDeleteUtil(imgdel);
			attributes.addFlashAttribute("result", "complete");

		} else {
			attributes.addFlashAttribute("result", "fail");

		}

		return "redirect:/notice_list";
	}

	/**
	 * notice_update 공지사항 - 수정
	 */
	@RequestMapping(value = "/notice_update", method = RequestMethod.GET)
	public ModelAndView noticeUpdate(String stat, String no) {
		ModelAndView model = new ModelAndView();

		NoticeVo noticeVo = noticeService.getNoticeContent(stat, no);

		model.addObject("noticeVo", noticeVo);
		model.setViewName("/notice/notice_update");

		return model;
	}

	/**
	 * notice_update_proc 공지사항 - 수정 처리
	 */
	@RequestMapping(value = "/notice_update_proc", method = RequestMethod.POST)
	public String noticeUpdateProc(NoticeVo noticeVo, HttpServletRequest request, RedirectAttributes attributes)
			throws Exception {
		String oldFileName = noticeVo.getImage_id();

		noticeVo = BoardUtil.fileUtil(request, noticeVo);
		int result = noticeService.update(noticeVo);
		if (result == 1) {
			BoardUtil.fileUpdateUtil(noticeVo, oldFileName);
			attributes.addFlashAttribute("result", "upsuccess");

		} else {
			attributes.addFlashAttribute("result", "fail");

		}

		return "redirect:/notice_content?stat=up&no=" + noticeVo.getPost_id();
	}

	/**
	 * comment_write_proc 댓글 - 작성 처리
	 */
	@RequestMapping(value = "comment_write_proc", method = RequestMethod.POST)
	@ResponseBody
	public String commentWriteProc(CommentVo commentVo, RedirectAttributes attributes) {

		String result = commentService.commentInsert(commentVo);

		return result;
	}

	/**
	 * comment_delete 댓글 - 삭제 처리
	 */
	@RequestMapping(value = "comment_delete", method = RequestMethod.POST)
	@ResponseBody
	public String commentDelete(@RequestParam("no") String no) {

		String result = commentService.delete(no);

		return result;
	}

	/**
	 * board_manage 리스트 선택 삭제 처리
	 */
	@RequestMapping(value = "board_manage", method = RequestMethod.POST)
	public String boardManage(String[] list) {

		noticeService.deleteList(list);

		return "redirect:/notice_list";
	}

	/**
	 * notice_Search 리스트 검색 처리
	 */
	@RequestMapping(value = "/notice_Search")
	@SuppressWarnings("unchecked")
	public ModelAndView boardSearchProc(String keyword, String page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView();

		Map<String, Integer> pageMap = BoardUtil.getPagination(page, keyword);
		List<NoticeVo> list = (List<NoticeVo>) noticeService.search(keyword, pageMap.get("startCount"),
				pageMap.get("endCount"));

		model.addObject("list", list);
		model.addObject("dbCount", pageMap.get("dbCount"));
		model.addObject("pageSize", pageMap.get("pageSize"));
		model.addObject("pageCount", pageMap.get("pageCount"));
		model.addObject("page", pageMap.get("reqPage"));

		model.setViewName("/notice/notice_list");

		return model;
	}
}
