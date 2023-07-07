package com.uniqueGames.controller;


import com.uniqueGames.fileutil.BoardUtil;
import com.uniqueGames.model.Comment;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Notice;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.service.CommentService;
import com.uniqueGames.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@SessionAttributes({SessionConstants.LOGIN_MEMBER, "list", "noticeVo"})
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final CommentService commentService;
    private final BoardUtil boardUtil;

//    @Autowired
//    public NoticeController(NoticeService noticeService, CommentService commentService, BoardUtil boardUtil) {
//        this.noticeService = noticeService;
//        this.commentService = commentService;
//        this.boardUtil = boardUtil;
//    }

    /**
     * notice/list 공지사항 목록 조회
     *
     * @param page
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/list")
    public String noticeList(String page, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Company company = new Company();
        company.setCompanyId("test");
        company.setName("TestGames");
        session.setAttribute(SessionConstants.LOGIN_MEMBER, company);

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
     * @param request
     * @param attributes
     * @return
     * @throws Exception
     */
    @PostMapping("/write")
    public String noticeWriteProc(Notice notice, @ModelAttribute(SessionConstants.LOGIN_MEMBER) Company company,
                                  HttpServletRequest request, RedirectAttributes attributes) throws Exception {
        notice = boardUtil.fileUtil(request, notice);
        notice.setCompanyId(company.getCompanyId());
        int result = noticeService.insert(notice);

        if (result == 1) {
            boardUtil.fileSaveUtil(notice);
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
    public String noticeContent(String stat, @PathVariable("no") String no, Model model) {
        Notice notice = noticeService.getNoticeContent(stat, no);
        List<Comment> commList = commentService.select(no);

        model.addAttribute("notice", notice);
        model.addAttribute("commList", commList);

        return "/notice/notice-content";
    }

    /**
     * notice/delete 공지사항 삭제 처리
     *
     * @param no
     * @param imgdel
     * @param attributes
     * @return
     */
    @PostMapping("/delete")
    public String noticeDelete(String no, String imgdel, RedirectAttributes attributes) {

        int result = noticeService.delete(no);
        if (result == 1) {
            boardUtil.fileDeleteUtil(imgdel);
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
    public ModelAndView noticeUpdate(@PathVariable("stat") String stat, @PathVariable("no") String no) {
        ModelAndView model = new ModelAndView();

        Notice notice = noticeService.getNoticeContent(stat, no);

        model.addObject("notice", notice);
        model.setViewName("/notice/notice-update");

        return model;
    }

    /**
     * noticeUpdateProc 공지사항 수정 처리
     * @param notice
     * @param request
     * @param attributes
     * @return
     * @throws Exception
     */
    @PostMapping("write/{stat}/{no}")
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

        return "redirect:/notice/content/" + notice.getPostId();
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
