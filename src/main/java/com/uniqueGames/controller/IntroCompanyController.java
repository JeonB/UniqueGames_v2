package com.uniqueGames.controller;

import com.uniqueGames.config.Login;
import com.uniqueGames.fileutil.FileUploadUtil;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Game;
import com.uniqueGames.model.Intro;
import com.uniqueGames.repository.CompanyRepositoryMapper;
import com.uniqueGames.service.GameService;
import com.uniqueGames.service.IndexServiceMapper;
import com.uniqueGames.service.IntroCompanyService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class IntroCompanyController {

    private final IntroCompanyService introCompanyService;
    private final IndexServiceMapper indexServiceMapper;
    private final GameService gameService;
    private final CompanyRepositoryMapper companyRepositoryMapper;
    @Autowired
    public IntroCompanyController(IntroCompanyService introCompanyService,
            IndexServiceMapper indexServiceMapper, GameService gameService,
            CompanyRepositoryMapper companyRepositoryMapper) {
        this.introCompanyService = introCompanyService;
        this.indexServiceMapper = indexServiceMapper;
        this.gameService = gameService;
        this.companyRepositoryMapper = companyRepositoryMapper;
    }
    /**
     * @param vo 회사 소개 저장객체
     * @return 이름 또는 제목이 null이면 회사등록 페이지 리턴. 아니면 회사 목록 페이지 리턴
     * @throws IOException 입출력 예외 처리
     */
    @PostMapping(value = "/insertIntro")
    public String insertIntro(Intro vo, Model model,@Login Company company,HttpSession session) throws IOException {

        FileUploadUtil fileUploadUtil = new FileUploadUtil() {
            /**
             * @param obj 인스턴스를 Intro 타입으로 변환 및 오버라이딩
             */
            @Override
            protected void extractFile(Object obj) {
                setFile(((Intro) obj).getUploadFile());
            }
        };
        vo.setCId(company.getCompanyId());
        vo.setUploadImg(fileUploadUtil.fileCheck(vo));
        introCompanyService.insertIntro(vo);
        session.setAttribute("status","writeOnce");
        return "redirect:getIntroList";
    }

    @GetMapping(value = "/insertIntro")
    public String writeIntro(Model model, @Login Company company) {
        model.addAttribute("company", company);
        return "detail/company-regi";
    }
    @RequestMapping(value = "/updateIntro", method = RequestMethod.POST)
    public String updateIntro(@ModelAttribute("intro") Intro vo, @ModelAttribute("company") Company company){
        introCompanyService.updateIntro(vo);
        return "detail/company-regi";
    }

    @RequestMapping(value = "/deleteIntro")
    public String deleteIntro(@ModelAttribute("intro") Intro vo,Model model){
        introCompanyService.deleteIntro(vo.getId());
        model.addAttribute("status", "");
        return "redirect:getIntroList";
    }

    /**
     * @param model  회사 소개 객체 담는 모델
     * @param vo     회사 소개 객체
     * @return 회사 소개 페이지
     */
    @GetMapping("/getIntro")
    public String getIntro(Model model, Intro vo){
        model.addAttribute("intro", introCompanyService.getIntro(vo.getId()));
        model.addAttribute("sliderList",indexServiceMapper.getGameList());
        return "detail/company";
    }

    /**
     * @param model 회사 소개페이지 리스트, 게임 객체 리스트 담는 객체
     * @return 회사 소개페이지 리스트
     */
    @GetMapping("/getIntroList")
    public String getIntroList(Model model){
        List<Intro> introList = introCompanyService.getIntroList();
        model.addAttribute("introList", introList);
        List<List<Game>> gameList = new ArrayList<>();
        Company company;
        for (Intro intro: introList){
            company = companyRepositoryMapper.findById(intro.getCId());
            gameList.add(gameService.getGameImg(company.getGId()));
        }

        List<List<Object>> combinedList = new ArrayList<>();
        combinedList.add(new ArrayList<>(introList));
        combinedList.add(new ArrayList<>(gameList));

        model.addAttribute("combinedList",combinedList);
        return "detail/company-list";
    }
}
