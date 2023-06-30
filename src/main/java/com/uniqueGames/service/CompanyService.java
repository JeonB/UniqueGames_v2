package com.uniqueGames.service;


import com.uniqueGames.model.IntroVo;
import java.util.List;

public interface CompanyService {
    void insertIntro(IntroVo vo);

    void updateIntro(IntroVo vo);

    void deleteIntro(IntroVo vo);

    IntroVo getIntro(int companyId);

    List<IntroVo> getIntroList();
}
