package com.uniqueGames.service;


import com.uniqueGames.model.Intro;
import java.util.List;

public interface CompanyService {
    void insertIntro(Intro vo);

    void updateIntro(Intro vo);

    void deleteIntro(Intro vo);

    Intro getIntro(int companyId);

    List<Intro> getIntroList();
}
