package com.uniqueGames.test;

import com.uniqueGames.model.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public abstract class GenericImpl2 {

    private MultipartFile file;
    private String imageId;

    @Value("${upload-directory}")
    private String ROOT_PATH;

    /**
     * MultipartFile을 필드 file에 넣도록 구현해야 함
     * @param obj
     */
    protected abstract void extractFile(Object obj);

    /**
     * 파일이 있는지 없는지 체크, 없으면 null 반환
     * @param obj
     * @return
     */
    public String fileCheck(Object obj) {
        extractFile(obj);
        if (file != null && !file.isEmpty()) {

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String upload_file = file.getOriginalFilename();
            String image_id = uuid + upload_file;

            setImageId(image_id);
            this.imageId = image_id;
        }

        return getImageId();
    }

    /**
     *
     */
    public void fileSaveUtil() {
        try {
            if (file != null && !file.isEmpty()) {
                File saveFile = new File(ROOT_PATH + this.getImageId());
                file.transferTo(saveFile);

            }
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void fileUpdateUtil(Notice notice, String oldFileName) throws Exception {
        String stat = "";

        if (oldFileName.indexOf("!") > -1) {
            String[] tmp = oldFileName.split("!");
            stat = tmp[0];
            oldFileName = tmp[1];
        }

        if (notice.getFile() != null && !notice.getFile().isEmpty()) {
            File saveFile = new File(ROOT_PATH + notice.getImageId());
            notice.getFile().transferTo(saveFile);

        }

        if (!notice.getFile().isEmpty() || stat.equals("delete")) {
            File deleteFile = new File(ROOT_PATH + oldFileName);

            if (deleteFile.exists()) {
                deleteFile.delete();

            }
        }
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
