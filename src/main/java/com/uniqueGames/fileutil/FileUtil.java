package com.uniqueGames.fileutil;

import com.uniqueGames.model.Intro;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {


    MultipartFile uploadFile;
    Intro vo;
    String root_path;


    public FileUtil(Intro vo, HttpServletRequest request){
        this.uploadFile = vo.getUploadFile();
        this.vo = vo;
        this.root_path = request.getSession().getServletContext().getRealPath("/");
    }
    // 랜덤 문자열 붙인 파일 저장 및 해당 파일 이름 저장한 객체 반환
    public Intro getUpload() throws IOException {
        String attach_path = "\\resources\\upload\\";
        if(uploadFile!=null && !uploadFile.isEmpty()){
            String fileName = uploadFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String upload = uuid + "_" + fileName;
            uploadFile.transferTo(new File(root_path + attach_path + upload));
            vo.setUpload(upload);
        }
        return vo;
    }

}
