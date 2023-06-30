package com.uniqueGames.service;


import com.uniqueGames.model.IntroVo;
import com.uniqueGames.repository.DetailMapper2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceMapper2 {

    @Autowired
    DetailMapper2 detailMapper2;

    public void insertIntro(IntroVo vo) {
        detailMapper2.insertIntro(vo);
    }

    public void updateIntro(IntroVo vo) {
        detailMapper2.updateIntro(vo);
    }

    public void deleteIntro(int id) {
        detailMapper2.deleteIntro(id);
    }

    public IntroVo getIntro(int id) {
        return detailMapper2.getIntro(id);
    }

    public List<IntroVo> getIntroList() {
        return detailMapper2.getIntroList();
    }
}
