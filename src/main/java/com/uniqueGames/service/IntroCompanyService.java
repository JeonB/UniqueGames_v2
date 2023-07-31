package com.uniqueGames.service;

import com.uniqueGames.fileutil.FileUploadUtil;
import com.uniqueGames.model.Intro;
import com.uniqueGames.model.Pagination;
import com.uniqueGames.repository.DetailMapper;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IntroCompanyService {

    @Autowired
    DetailMapper detailMapper;

    @Value("${upload-directory}")
    private String ROOT_PATH;
    FileUploadUtil fileUploadUtil = new FileUploadUtil() {
        /**
         * @param obj 인스턴스를 Intro 타입으로 변환 및 오버라이딩
         */
        @Override
        protected void extractFile(Object obj) {
            setFile(((Intro) obj).getUploadFile());
        }
    };

    public void insertIntro(Intro intro) throws IOException {

        String filename = fileUploadUtil.fileCheck(intro);
        intro.setUploadImg(filename);
        fileUploadUtil.save(ROOT_PATH, filename, intro.getUploadFile().getBytes());
        detailMapper.insertIntro(intro);
    }

    public void updateIntro(Intro intro) throws IOException {
        String filename = fileUploadUtil.fileCheck(intro);
        intro.setUploadImg(filename);
        fileUploadUtil.save(ROOT_PATH, filename, intro.getUploadFile().getBytes());
        detailMapper.updateIntro(intro);
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
    public List<Intro> findByAllPaginationAndSearch(Pagination pagination){
        return detailMapper.findByAllPaginationAndSearch(pagination);
    };

    public Integer findIdByCId(String cId) {
        return detailMapper.findIdByCId(cId);
    }

    public void oldFileDelete(String imageName) {
        fileUploadUtil.fileDelete(imageName,ROOT_PATH);
    }
}
