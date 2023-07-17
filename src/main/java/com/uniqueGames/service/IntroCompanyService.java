package com.uniqueGames.service;


import com.uniqueGames.model.Intro;
import com.uniqueGames.repository.DetailMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntroCompanyService {

    @Autowired
    DetailMapper detailMapper;

    public void insertIntro(Intro vo) {
        detailMapper.insertIntro(vo);
    }

    public void updateIntro(Intro vo) {
        detailMapper.updateIntro(vo);
    }

    public void deleteIntro(int id) {
        detailMapper.deleteIntro(id);
    }

    public Intro getIntro(int id) {
        return detailMapper.getIntro(id);
    }

    public List<Intro> getIntroList() {
        return detailMapper.getIntroList();
    }
}
