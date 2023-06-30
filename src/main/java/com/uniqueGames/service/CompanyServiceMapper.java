package com.uniqueGames.service;


import com.uniqueGames.model.IntroVo;
import com.uniqueGames.repository.DetailMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceMapper{

    @Autowired
    DetailMapper detailMapper;

    public void insertIntro(IntroVo vo) {
        detailMapper.insertIntro(vo);
    }

    public void updateIntro(IntroVo vo) {
        detailMapper.updateIntro(vo);
    }

    public void deleteIntro(int id) {
        detailMapper.deleteIntro(id);
    }

    public IntroVo getIntro(int id) {
        return detailMapper.getIntro(id);
    }

    public List<IntroVo> getIntroList() {
        return detailMapper.getIntroList();
    }
}
