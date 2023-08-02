package com.uniqueGames.controller;


import com.uniqueGames.config.Login;
import com.uniqueGames.fileutil.BoardUtil;
import com.uniqueGames.model.Comment;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Notice;
import com.uniqueGames.service.CommentService;
import com.uniqueGames.service.NoticeService;
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
public class NoticeController {

    private final NoticeService noticeService;
    private final CommentService commentService;
    private final BoardUtil boardUtil;

    @Autowired
    public NoticeController(NoticeService noticeService, CommentService commentService, BoardUtil boardUtil) {
        this.noticeService = noticeService;
        this.commentService = commentService;
        this.boardUtil = boardUtil;
    }

    /**
     * notice/list 공지사항 목록 조회
     *
     * @param page 페이지 정보
     */
    @GetMapping({"/list", "/list/{page}"})
    public String noticeList(@PathVariable(value = "page", required = false) String page, Model model, @ModelAttribute("result") String result) {

        // 페이징 처리 - startCount, endCount 구하기
        Map<String, Integer> pageMap = boardUtil.getPagination(page, "list", "");
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
     * /notice/list/search 검색 처리
     *
     * @param q          검색어
     * @param page       페이지 정보
     * @param searchType 검색 타입
     */
    @GetMapping(value = "/list/search")
    public String boardSearchProc(String q, String page, Model model, String searchType) {
        Map<String, Integer> pageMap = boardUtil.getPagination(page, q, searchType);
        List<Notice> list = noticeService.search(q, pageMap, searchType);

        model.addAttribute("list", list);
        model.addAttribute("dbCount", pageMap.get("dbCount"));
        model.addAttribute("pageSize", pageMap.get("pageSize"));
        model.addAttribute("pageCount", pageMap.get("pageCount"));
        model.addAttribute("page", pageMap.get("reqPage"));

        return "notice/notice-list";
    }

    /**
     * notice/write 공지사항 작성 페이지 이동
     */
    @GetMapping("/write")
    public String noticeWrite() {
        return "notice/notice-write";
    }

    /**
     * noticeWriteProc 공지사항 작성 처리
     *
     * @param notice     폼에 저장된 정보
     * @param company    로그인한 회사 정보
     */
    @PostMapping("/write")
    public String noticeWriteProc(Notice notice, @Login Company company,
                                  RedirectAttributes attributes) {
        notice.setCId(company.getCompanyId());
        int result = noticeService.insert(notice);
        if (result == 1) {
            attributes.addFlashAttribute("result", "insuccess");

        } else {
            attributes.addFlashAttribute("result", "fail");

        }
        return "redirect:/notice/content/" + notice.getId();
    }

    /**
     * notice/content/{no} 공지사항 상세 보기
     *
     * @param stat  수정 이후면 up이 들어옴
     * @param no    페이지 번호
     */
    @GetMapping("/content/{no}")
    public String noticeContent(String stat, @PathVariable("no") String no, Model model, @ModelAttribute("result") String result) {
        Notice notice = noticeService.getNoticeContent(stat, no);
        List<Comment> commList = commentService.select(no);

        model.addAttribute("notice", notice);
        model.addAttribute("commList", commList);
        model.addAttribute("result", result);

        return "notice/notice-content";
    }

    /**
     * notice/delete 공지사항 삭제 처리
     *
     * @param no         페이지 번호
     * @param imgDel     삭제할 이미지
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
     * @param stat 수정 이후면 up이 들어옴
     * @param no   페이지 번호
     */
    @GetMapping("/write/{stat}/{no}")
    public String noticeUpdate(@PathVariable("stat") String stat, @PathVariable("no") String no, Model model) {

        Notice notice = noticeService.getNoticeContent(stat, no);

        model.addAttribute("notice", notice);

        return "notice/notice-update";
    }

    /**
     * noticeUpdateProc 공지사항 수정 처리
     *
     * @param notice     폼 데이터
     */
    @PostMapping("/write/{stat}/{no}")
    @SuppressWarnings("unused")
    public String noticeUpdateProc(Notice notice, RedirectAttributes attributes, @PathVariable String no, @PathVariable String stat) {
        int result = noticeService.update(notice);
        if (result == 1) {
            attributes.addFlashAttribute("result", "upsuccess");

        } else {
            attributes.addFlashAttribute("result", "fail");

        }

        return "redirect:/notice/content/" + notice.getId();
    }

    /**
     * notice/popUp 팝업 창
     */
    @GetMapping("popUp")
    public String noticePopUp() {
        return "notice/report-pop-up";
    }
}
