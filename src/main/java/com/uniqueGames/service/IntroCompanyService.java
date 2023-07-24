package com.uniqueGames.service;


import com.uniqueGames.model.Intro;
import com.uniqueGames.model.Pagination;
import com.uniqueGames.repository.DetailMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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

    public String pageProcess(Pagination pagination){
        pagination.setListLastNum(detailMapper.findByAllCount());

        log.info("pagination.getPage()={}", pagination.getPage());
        if(pagination.getPage() < 1){
            return "getIntroList?page=1";
        }
        if(pagination.getPage() > pagination.getListLastNum()){
            return "getIntroList?page="+pagination.getListLastNum();
        }

        pagination.setterProcess();

        return null;
    }
}
