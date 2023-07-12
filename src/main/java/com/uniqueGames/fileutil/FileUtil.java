package com.uniqueGames.fileutil;

import com.uniqueGames.model.Intro;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import com.uniqueGames.model.Notice;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil<T> {


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

    /**
     * 파일 체크
     *
     * @param request
     * @param notice
     * @return
     * @throws Exception
     */
    public Notice fileUtil(HttpServletRequest request, Notice notice) throws Exception {

        if (notice.getFile() != null && !notice.getFile().isEmpty()) {

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String upload_file = notice.getFile().getOriginalFilename();
            String image_id = uuid + upload_file;

            notice.setUploadFile(upload_file);
            notice.setImageId(image_id);

        }

        return notice;
    }

    /**
     * 파일 저장
     *
     * @param notice
     * @throws Exception
     */
    public  void fileSaveUtil(Notice notice) throws Exception {

        if (notice.getFile() != null && !notice.getFile().isEmpty()) {
            File saveFile = new File(root_path + notice.getImageId());
            notice.getFile().transferTo(saveFile);

        }

    }

    /**
     * 파일 업데이트
     * @param notice
     * @throws Exception
     */
    public void fileUpdateUtil(Notice notice, String oldFileName) throws Exception {
        String stat = "";

        if (oldFileName.indexOf("!") > -1) {
            String[] tmp = oldFileName.split("!");
            stat = tmp[0];
            oldFileName = tmp[1];
        }

        if (notice.getFile() != null && !notice.getFile().isEmpty()) {
            File saveFile = new File(root_path + notice.getImageId());
            notice.getFile().transferTo(saveFile);

        }

        if (!notice.getFile().isEmpty() || stat.equals("delete")) {
            File deleteFile = new File(root_path + oldFileName);

            if (deleteFile.exists()) {
                deleteFile.delete();

            }
        }
    }

    /**
     * 파일 삭제
     * @param imgdel
     */
    public void fileDeleteUtil(String imgdel) {

        if (imgdel != null && !imgdel.equals("")) {
            File deleteFile = new File(root_path + imgdel);
            if (deleteFile.exists()) {
                deleteFile.delete();

            }

        }
    }

}
