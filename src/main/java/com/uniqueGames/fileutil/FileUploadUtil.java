package com.uniqueGames.fileutil;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public abstract class FileUploadUtil {

    private MultipartFile file;
    private String imageName;

    @Value("${upload-directory}")
    private String ROOT_PATH;

    /**
     * MultipartFile 타입의 데이터를 필드 file에 넣도록 구현해야 함
     * @param obj
     */
    protected abstract void extractFile(Object obj);

    /**
     * 파일이 있는지 체크
     * <p>있으면 변환된 이미지 이름 반환, 없으면 null 반환</p>
     * @param obj
     * @return 변환된 이미지 이름
     */
    public String fileCheck(Object obj) {
        // 추상화 메소드를 구현한 메소드에서 파일 추출
        extractFile(obj);

        if (file != null && !file.isEmpty()) {

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String upload_file = file.getOriginalFilename();
            String image_id = uuid + upload_file;

            setImageName(image_id);
        }

        return getImageName();
    }

    /**
     * 파일 체크 이후 저장된 필드 값을 이용해 파일 저장
     */
    public void fileSave() {
        try {
            if (file != null && !file.isEmpty()) {
                File saveFile = new File(ROOT_PATH + getImageName());
                file.transferTo(saveFile);

            }
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    /**
     * <p>수정을 통해 파일을 바꾸고자 할 때, 파일을 삭제하고자 할 때 사용</p>
     * <p>이전 파일을 삭제하기 위해 이전 이미지 이름을 넘겨줘야함</p>
     * <p>이미지 교체 없이 삭제 로직을 사용하려면 이미지 값을 "delete!이미지 이름" 으로 바꿔야함;;</p>
     * @param oldFileName
     * @return "" or "delete"
     */
    public String fileUpdate(String oldFileName) {
        String stat = "";

        if (oldFileName.indexOf("!") > -1) {
            String[] tmp = oldFileName.split("!");
            stat = tmp[0];
            oldFileName = tmp[1];
        }

        // 새로운 파일 저장
        fileSave();

        // 새 파일이 저장됐거나, 파일 취소 버튼을 눌러 기존 파일을 삭제할 때
        if (!file.isEmpty() || stat.equals("delete")) {
            File deleteFile = new File(ROOT_PATH + oldFileName);

            if (deleteFile.exists()) {
                deleteFile.delete();

            }
        }

        return stat;
    }

    /**
     * 아 귀찮다 메소드 하나만 쓰고싶다 하면 쓰세요
     * <p>수정을 통해 파일을 바꾸고자 할 때, 파일을 삭제하고자 할 때 사용</p>
     * <p>이전 파일을 삭제하기 위해 이전 이미지 이름을 넘겨줘야함</p>
     * <p>이미지 교체 없이 삭제 로직을 사용하려면 이미지 값을 "delete!이미지 이름" 으로 바꿔야함;;</p>
     * @param obj
     * @param oldFileName
     * @return String[] 0 = 파일 이름, 1 = "" or "delete"
     *
     */
    public String[] fileUpdate(Object obj, String oldFileName) {
        fileCheck(obj);
        String stat = fileUpdate(oldFileName);

        return new String[] {imageName, stat};
    }

    /**
     * 삭제할 이미지 이름을 이용해 이미지 삭제
     * @param imageName
     */
    public void fileDelete(String imageName) {

        if (imageName != null && !imageName.equals("")) {
            File deleteFile = new File(ROOT_PATH + imageName);
            if (deleteFile.exists()) {
                deleteFile.delete();

            }

        }
    }

    /**
     * 여러 파일 삭제하기
     * @param list<String>
     */
    public void fileListDelete(List<String> list) {
        for (String imageName : list) {
            if (imageName != null && !imageName.equals("")) {
                File deleteFile = new File(ROOT_PATH + imageName);
                if (deleteFile.exists())
                    deleteFile.delete();

            }
        }
    }

}
