package com.uniqueGames.controller;


import com.uniqueGames.fileutil.FileUtil;
import com.uniqueGames.model.*;
import com.uniqueGames.model.Company;
import com.uniqueGames.repository.CompanyRepositoryMapper;
import com.uniqueGames.service.CompanyServiceMapper;
import com.uniqueGames.service.IndexServiceMapper;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author jeonb 
 * 회사 소개 페이지 Mybatis 컨트롤러
 * CRUD 메소드 구현
 */
@Controller
@SessionAttributes({"intro","status","game","gameName","companyVo","list","noticeVo","company"})
@RequestMapping(value = "/detail")
public class DetailMapperController {


    private final CompanyServiceMapper companyServiceMapper;
    private final IndexServiceMapper indexServiceMapper;
    CompanyRepositoryMapper companyRepositoryMapper;

    @Autowired
    public DetailMapperController(CompanyServiceMapper companyServiceMapper,
            IndexServiceMapper indexServiceMapper, CompanyRepositoryMapper companyRepositoryMapper) {
        this.companyServiceMapper = companyServiceMapper; // 매퍼 방식
        this.indexServiceMapper = indexServiceMapper;
        this.companyRepositoryMapper = companyRepositoryMapper;
    }

    /** goDetail()
     * @return 회사 상세페이지 리턴
     */
    @RequestMapping(value = "/{detailId}", method = RequestMethod.GET)
    public String goDetail(@PathVariable("detailId") int detailId, Model model) {
        Game game = indexServiceMapper.getGameForIndex(detailId);
        model.addAttribute("game", game);
        Company company = companyRepositoryMapper.findByIndex(detailId);
        model.addAttribute("companyVo", company);
        // 요청된 detailId에 따라 해당 페이지로 이동
        switch (detailId) {
            case 1:
                return "detail/detail";
            case 2:
                return "detail/detail2";
            case 3:
                return "detail/detail3";
            case 4:
                return "detail/detail4";
            default:
                return "redirect:/";
        }
    }
//    @RequestMapping(value = "/{detailId}", method = RequestMethod.POST)
//    public void getCompanyId(@PathVariable("detailId") int detailId,@RequestParam("companyId") String companyId, Model model){
//        Company companyVo = companyRepositoryMapper.findById(companyId);
//        model.addAttribute("companyVo", companyVo);
//    }

    /**
     * @param vo 회사 소개 저장객체
     * @param request 현재 url을 가져오기 위한 객체
     * @return 이름 또는 제목이 null이면 회사등록 페이지 리턴. 아니면 회사 목록 페이지 리턴
     * @throws IOException 입출력 예외 처리
     */
    @RequestMapping(value = "/insertIntro")
    public String insertIntro(Intro vo, HttpServletRequest request,Model model) throws IOException {

        FileUtil fileUtil = new FileUtil(vo, request);
        Intro intro = fileUtil.getUpload();
        HttpSession session = request.getSession();

        if(intro.getTitle() == null || intro.getName() == null){
                Company company = (Company) session.getAttribute(SessionConstants.LOGIN_MEMBER);
                model.addAttribute("company",company);
                return "detail/company_regi";
         }
        else{
                companyServiceMapper.insertIntro(intro);
                model.addAttribute("status", "writeOnce");
                return "redirect:getIntroList";
            }
    }
//    @RequestMapping(value = "/updateIntro")
//    public String updateIntro(@ModelAttribute("intro") Intro vo, @ModelAttribute("company") Company companyVo){
//        companyServiceMapper.updateIntro(vo);
//        return "redirect:getIntroList";
//    }
    @RequestMapping(value = "/updateIntro", method = RequestMethod.POST)
    public String updateIntro(@ModelAttribute("intro") Intro vo, @ModelAttribute("company") Company company){
        companyServiceMapper.updateIntro(vo);
        return "detail/company_regi";
    }

    @RequestMapping(value = "/deleteIntro")
    public String deleteIntro(@ModelAttribute("intro") Intro vo,Model model){
        companyServiceMapper.deleteIntro(vo.getId());
        model.addAttribute("status", "");
        return "redirect:getIntroList";
    }

    @RequestMapping(value = "/getIntro")
    public String getIntro(Model model, Intro vo, @ModelAttribute("noticeVo") Notice notice, @ModelAttribute("list") ArrayList<Notice> list){
        model.addAttribute("intro",companyServiceMapper.getIntro(vo.getId()));
        return "detail/company";
    }
    @RequestMapping(value = "/getIntroList")
    public String getIntroList(Model model){
        model.addAttribute("companyList", companyServiceMapper.getIntroList());
        return "detail/company_list";
    }

    @RequestMapping(value="/popUp", method = RequestMethod.POST)
    public String postPopUp(@RequestParam(value = "gameName",defaultValue = "default") String gameName, Model model){
        model.addAttribute("gameName", gameName);
        return "detail/popup";
    }

    @RequestMapping(value="/popUp", method = RequestMethod.GET)
    public String getPopUp(@ModelAttribute("companyVo") Company company){
        return "detail/popup";
    }
}
