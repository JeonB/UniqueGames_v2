package com.uniqueGames.controller;


import com.uniqueGames.config.Login;
import com.uniqueGames.fileutil.BoardUtil;
import com.uniqueGames.model.Comment;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Notice;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.service.CommentService;
import com.uniqueGames.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"list", "noticeVo"})
@RequestMapping(value = "/notice")
@Slf4j
public class NoticeController {

    private NoticeService noticeService;
    private CommentService commentService;
    private BoardUtil boardUtil;

    @Autowired
    public NoticeController(NoticeService noticeService, CommentService commentService, BoardUtil boardUtil) {
        this.noticeService = noticeService;
        this.commentService = commentService;
        this.boardUtil = boardUtil;
    }

    /**
     * notice/list 공지사항 목록 조회
     *
     * @param page
     * @param model
     * @return
     */
    @GetMapping({"/list", "/list/{page}"})
    public String noticeList(@PathVariable(value = "page", required = false) String page, Model model, @ModelAttribute("result") String result) {

        // 페이징 처리 - startCount, endCount 구하기
        Map<String, Integer> pageMap = boardUtil.getPagination(page, "list", "");
//        Page pageInfo = boardUtil.getPagination(new Page(page, "list"));
        List<Notice> list = noticeService.getNoticeList(pageMap.get("startCount"), pageMap.get("endCount"));
        model.addAttribute("list", list);
        model.addAttribute("dbCount", pageMap.get("dbCount"));
        model.addAttribute("pageSize", pageMap.get("pageSize"));
        model.addAttribute("pageCount", pageMap.get("pageCount"));
        model.addAttribute("page", pageMap.get("reqPage"));
        model.addAttribute("result", result);

        return "notice/notice-list";
    }

    /**
     * notice/write 공지사항 작성 페이지 이동
     *
     * @return
     */
    @GetMapping("/write")
    public String noticeWrite() {
        return "notice/notice-write";
    }

    /**
     * noticeWriteProc 공지사항 작성 처리
     *
     * @param notice
     * @param company
     * @param attributes
     * @return
     * @throws Exception
     */
    @PostMapping("/write")
    public String noticeWriteProc(Notice notice, @Login Company company,
                                  RedirectAttributes attributes) {
        notice.setCompanyId(company.getCompanyId());
        int result = noticeService.insert(notice);
        if (result == 1) {
            attributes.addFlashAttribute("result", "insuccess");

        } else {
            attributes.addFlashAttribute("result", "fail");

        }
        return "redirect:/notice/content/" + notice.getPostId();
    }

    /**
     * notice/content/{no} 공지사항 상세 보기
     *
     * @param stat
     * @param no
     * @param model
     * @return
     */
    @GetMapping("/content/{no}")
    public String noticeContent(String stat, @PathVariable("no") String no, Model model, @ModelAttribute("result") String result) {
        Notice notice = noticeService.getNoticeContent(stat, no);
        List<Comment> commList = commentService.select(no);

        model.addAttribute("notice", notice);
        model.addAttribute("commList", commList);
        model.addAttribute("result", result);

        return "/notice/notice-content";
    }

    /**
     * notice/delete 공지사항 삭제 처리
     *
     * @param no
     * @param imgDel
     * @param attributes
     * @return
     */
    @PostMapping("/delete")
    public String noticeDelete(String no, String imgDel, RedirectAttributes attributes) {

        int result = noticeService.delete(no, imgDel);
        if (result == 1) {
            attributes.addFlashAttribute("result", "complete");

        } else {
            attributes.addFlashAttribute("result", "fail");

        }

        return "redirect:/notice/list";
    }

    /**
     * notice/write/{stat}/{no} 공지사항 수정 페이지
     *
     * @param stat
     * @param no
     * @return
     */
    @GetMapping("write/{stat}/{no}")
    public String noticeUpdate(@PathVariable("stat") String stat, @PathVariable("no") String no, Model model) {

        Notice notice = noticeService.getNoticeContent(stat, no);

        model.addAttribute("notice", notice);

        return "/notice/notice-update";
    }

    /**
     * noticeUpdateProc 공지사항 수정 처리
     *
     * @param notice
     * @param attributes
     * @return
     * @throws Exception
     */
    @PostMapping("write/{stat}/{no}")
    public String noticeUpdateProc(Notice notice, RedirectAttributes attributes) {
        int result = noticeService.update(notice);
        if (result == 1) {
            attributes.addFlashAttribute("result", "upsuccess");

        } else {
            attributes.addFlashAttribute("result", "fail");

        }

        return "redirect:/notice/content/" + notice.getPostId();
    }

    /**
     * notice_Search 리스트 검색 처리
     */
    @GetMapping(value = "/list/search")
    @SuppressWarnings("unchecked")
    public String boardSearchProc(String q, String page, Model model, String searchType) {
        log.info(searchType);
        Map<String, Integer> pageMap = boardUtil.getPagination(page, q, searchType);
        List<Notice> list = (List<Notice>) noticeService.search(q, pageMap, searchType);

        model.addAttribute("list", list);
        model.addAttribute("dbCount", pageMap.get("dbCount"));
        model.addAttribute("pageSize", pageMap.get("pageSize"));
        model.addAttribute("pageCount", pageMap.get("pageCount"));
        model.addAttribute("page", pageMap.get("reqPage"));

        return "/notice/notice-list";
    }
}
