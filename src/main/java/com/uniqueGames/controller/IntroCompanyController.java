package com.uniqueGames.controller;

import com.uniqueGames.config.Login;
import com.uniqueGames.fileutil.FileUploadUtil;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Game;
import com.uniqueGames.model.Intro;
import com.uniqueGames.model.Member;
import com.uniqueGames.model.Pagination;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.repository.CompanyRepositoryMapper;
import com.uniqueGames.service.GameService;
import com.uniqueGames.service.IndexServiceMapper;
import com.uniqueGames.service.IntroCompanyService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @param intro 회사 소개 저장객체 ajax 처리됨
     * @throws IOException 입출력 예외 처리
     */
    @PostMapping(value = "/insertIntro")
    public String insertIntro(Intro intro,@Login Company company,HttpSession session) throws IOException {

        intro.setCId(company.getCompanyId());
        introCompanyService.insertIntro(intro);
        session.setAttribute("status","writeOnce");
        return "redirect:/getIntroList";
    }

    /**
     * @param model 회사 인스턴스 담을 모델 객체
     * @param company 로그인 어노테이션으로 주입된 회사 인스턴스
     * @return 회사 소개 등록 페이지
     */
    @GetMapping(value = "/insertIntro")
    public String writeIntro(Model model, @Login Company company) {
        model.addAttribute("company", company);
        return "detail/company-regi";
    }

    /**
     * @param id 수정하려는 intro 인스턴스의 id
     * @param company 기업 회원 로그인이 유지되고 있는지 확인하기 위함
     * @param model id와 일치하는 intro 인스턴스 정보와 기업회원 인스턴스를 담을 객체
     * @return 소개글 작성했던 페이지 호출
     */
    @GetMapping(value = "/editIntro/{id}")
    public String updateIntro(@PathVariable("id") int id, @Login Company company, Model model){
        Intro intro = introCompanyService.getIntro(id);
        introCompanyService.oldFileDelete(intro.getUploadImg()); // 이전 파일 삭제
        model.addAttribute("intro", intro);
        model.addAttribute("company", company);
        return "detail/company-regi";
    }

    /**
     * @param intro 수정 완료된 intro 인스턴스
     * 해당 인스턴스는 ajax를 통해 처리됨
     */
    @PostMapping(value = "/editIntro")
    public String updateIntroSave(Intro intro) throws IOException {
        introCompanyService.updateIntro(intro);
        return "redirect:/getIntroList";
    }

    /**
     * @param id 회사 소개 게시글 아이디
     * @param session 해당 게시글 삭제시 status 세션 삭제
     * @return 삭제 완료 후 회사 리스트 페이지로 이동
     */
    @RequestMapping(value = "/deleteIntro/{id}")
    public String deleteIntro(@PathVariable("id") int id, HttpSession session){
        introCompanyService.deleteIntro(id);
        session.removeAttribute("status");
        return "redirect:/getIntroList";
    }


    /**
     * @param model 회사 소개페이지 리스트, 게임 객체 리스트 담는 객체
     * @return 회사 소개페이지 리스트
     */
    @GetMapping("/getIntroList")
    public String getIntroList(@ModelAttribute Pagination pagination,Model model, HttpSession session){
        
        // 회원 세션 존재 여부 확인 및 기업 회원 의존성 주입
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER)!= null && session.getAttribute(SessionConstants.LOGIN_MEMBER).getClass() == Company.class) {
            Company company = (Company) session.getAttribute(SessionConstants.LOGIN_MEMBER);
            Optional<Integer> optionalId = Optional.ofNullable(
                    introCompanyService.findIdByCId(company.getCompanyId()));
            if(optionalId.isPresent()){
                int id = optionalId.orElse(0);
                model.addAttribute("id",id);
                session.setAttribute("status","writeOnce");
            }
        }

        String redirectUrl = introCompanyService.pageProcess(pagination); //페이지네이션
        if(redirectUrl != null){
            return "redirect:/"+redirectUrl;
        }
        List<Intro> lists = introCompanyService.findByAllPaginationAndSearch(pagination);
        List<List<Game>> gameList = new ArrayList<>(); // 다수의 게임 이미지를 담기 위한 2차원 리스트
        Company comp;
        for (Intro intro: lists){
            comp = companyRepositoryMapper.findById(intro.getCId()); // intro 인스턴스의 회사 아이디와 일치하는 company 인스턴스 호출
            gameList.add(gameService.getGameImg(comp.getGId())); // 호출된 company 인스턴스의 gid와 일치하는 List<game> 타입의 게임 인스턴스 호출
        }
        model.addAttribute("baseUrl", "getIntroList");

        List<List<Object>> combinedList = new ArrayList<>();
        combinedList.add(new ArrayList<>(lists));
        combinedList.add(new ArrayList<>(gameList));
        model.addAttribute("combinedList",combinedList);

        return "detail/company-list";
    }

}
