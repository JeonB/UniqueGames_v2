package com.uniqueGames.controller;


import com.uniqueGames.fileutil.BoardUtil;
import com.uniqueGames.model.Comment;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Notice;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.service.CommentService;
import com.uniqueGames.service.NoticeService;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({SessionConstants.LOGIN_MEMBER, "list", "noticeVo"})
public class NoticeController {

    NoticeService noticeService;
    CommentService commentService;
    BoardUtil boardUtil;

    @Autowired
    public NoticeController(NoticeService noticeService, CommentService commentService, BoardUtil boardUtil) {
        this.noticeService = noticeService;
        this.commentService = commentService;
        this.boardUtil = boardUtil;
    }

    /**
     * notice-list 공지사항 - 전체 리스트
     */
    @GetMapping("/notice-list")
    public String noticeList(String page, Model model) {

        // 페이징 처리 - startCount, endCount 구하기
        Map<String, Integer> pageMap = boardUtil.getPagination(page, "list");
        List<Notice> list = noticeService.getNoticeList(pageMap.get("startCount"), pageMap.get("endCount"));
        model.addAttribute("list", list);
        model.addAttribute("dbCount", pageMap.get("dbCount"));
        model.addAttribute("pageSize", pageMap.get("pageSize"));
        model.addAttribute("pageCount", pageMap.get("pageCount"));
        model.addAttribute("page", pageMap.get("reqPage"));

        return "notice/notice-list";
    }

    /**
     * notice_write 공지사항 - 작성
     */
    @RequestMapping(value = "/notice_write", method = RequestMethod.GET)
    public String noticeWrite() {
        return "notice/notice-write";
    }

    /**
     * notice_write_proc 공지사항 - 작성 처리
     */
    @RequestMapping(value = "/notice_write_proc", method = RequestMethod.POST)
    public String noticeWriteProc(Notice notice, @ModelAttribute(SessionConstants.LOGIN_MEMBER) Company cvo,
                                  HttpServletRequest request, RedirectAttributes attributes) throws Exception {

        notice = boardUtil.fileUtil(request, notice);
        notice.setCompanyId(cvo.getCompanyId());
        int result = noticeService.insert(notice);

        if (result == 1) {
            boardUtil.fileSaveUtil(notice);
            attributes.addFlashAttribute("result", "insuccess");

        } else {
            attributes.addFlashAttribute("result", "fail");

        }

        return "redirect:/notice_content?no=" + notice.getPostId();
    }

    /**
     * notice_content 공지사항 - 상세 보기
     */
    @RequestMapping(value = "/notice_content", method = RequestMethod.GET)
    public ModelAndView noticeContent(String stat, String no) {
        ModelAndView model = new ModelAndView();

        Notice notice = noticeService.getNoticeContent(stat, no);
        List<Comment> commList = commentService.select(no);

        model.addObject("noticeVo", notice);
        model.addObject("commList", commList);
        model.setViewName("/notice/notice-content");

        return model;
    }

    /**
     * notice_delete 공지사항 - 삭제
     */
    @RequestMapping(value = "/notice_delete", method = RequestMethod.POST)
    public String noticeDelete(String no, String imgdel, RedirectAttributes attributes) {

        int result = noticeService.delete(no);
        if (result == 1) {
            boardUtil.fileDeleteUtil(imgdel);
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

        Notice notice = noticeService.getNoticeContent(stat, no);

        model.addObject("noticeVo", notice);
        model.setViewName("/notice/notice-update");

        return model;
    }

    /**
     * notice_update_proc 공지사항 - 수정 처리
     */
    @RequestMapping(value = "/notice_update_proc", method = RequestMethod.POST)
    public String noticeUpdateProc(Notice notice, HttpServletRequest request, RedirectAttributes attributes)
            throws Exception {
        String oldFileName = notice.getImageId();

        notice = boardUtil.fileUtil(request, notice);
        int result = noticeService.update(notice);
        if (result == 1) {
            boardUtil.fileUpdateUtil(notice, oldFileName);
            attributes.addFlashAttribute("result", "upsuccess");

        } else {
            attributes.addFlashAttribute("result", "fail");

        }

        return "redirect:/notice_content?stat=up&no=" + notice.getPostId();
    }

    /**
     * comment_write_proc 댓글 - 작성 처리
     */
    @RequestMapping(value = "comment_write_proc", method = RequestMethod.POST)
    @ResponseBody
    public String commentWriteProc(Comment comment) {

        return commentService.commentInsert(comment);
    }

    /**
     * comment_delete 댓글 - 삭제 처리
     */
    @RequestMapping(value = "comment_delete", method = RequestMethod.POST)
    @ResponseBody
    public String commentDelete(@RequestParam("no") String no) {

        return commentService.delete(no);
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
    public ModelAndView boardSearchProc(String keyword, String page) {
        ModelAndView model = new ModelAndView();

        Map<String, Integer> pageMap = boardUtil.getPagination(page, keyword);
        List<Notice> list = (List<Notice>) noticeService.search(keyword, pageMap.get("startCount"),
                pageMap.get("endCount"));

        model.addObject("list", list);
        model.addObject("dbCount", pageMap.get("dbCount"));
        model.addObject("pageSize", pageMap.get("pageSize"));
        model.addObject("pageCount", pageMap.get("pageCount"));
        model.addObject("page", pageMap.get("reqPage"));

        model.setViewName("/notice/notice-list");

        return model;
    }
}
