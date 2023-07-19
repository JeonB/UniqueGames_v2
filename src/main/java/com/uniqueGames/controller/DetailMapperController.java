package com.uniqueGames.controller;


import com.uniqueGames.fileutil.FileUtil;
import com.uniqueGames.model.*;
import com.uniqueGames.model.Company;
import com.uniqueGames.repository.CompanyRepositoryMapper;
import com.uniqueGames.service.IntroCompanyService;
import com.uniqueGames.service.IndexServiceMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@SessionAttributes({"intro","status","game","gameName","company"})
@RequestMapping(value = "/detail")
public class DetailMapperController {



    private final IndexServiceMapper indexServiceMapper;
    CompanyRepositoryMapper companyRepositoryMapper;

    @Autowired
    public DetailMapperController(IndexServiceMapper indexServiceMapper, CompanyRepositoryMapper companyRepositoryMapper) {
        this.indexServiceMapper = indexServiceMapper;
        this.companyRepositoryMapper = companyRepositoryMapper;
    }

    /** goDetail()
     * @return 회사 상세페이지 리턴
     */
    @GetMapping("/{detailId}")
    public String goDetail(@PathVariable("detailId") int detailId, Model model) {
        Game game = indexServiceMapper.getGameForIndex(detailId);
        model.addAttribute("game", game);
        Company company = companyRepositoryMapper.findByIndex(detailId);
        model.addAttribute("company", company);
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

    @PostMapping("/popUp")
    public String postPopUp(@RequestParam(value = "gameName",defaultValue = "default") String gameName, Model model){
        model.addAttribute("gameName", gameName);
        return "detail/pop-up";
    }

    /**
     * @return 후원 팝업창 리턴
     */
    @GetMapping("/popUp")
    public String getPopUp(){
        return "detail/pop-up";
    }
}
