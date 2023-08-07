package com.uniqueGames.fileutil;

import java.io.File;

public class CommonUtils {
    private static final String FILE_EXTENSION_SEPARATOR = ".";

    public static String buildFileName(String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR); //파일 확장자 구분선
        String fileExtension = originalFileName.substring(fileExtensionIndex); //파일 확장자
        String fileName = originalFileName.substring(0, fileExtensionIndex);
        String now = String.valueOf(System.currentTimeMillis());

        return fileName + "_" + now + fileExtension;
    }
    public void fileDelete(String fileUrl) {

        if (fileUrl != null && !fileUrl.isEmpty()) {
            File deleteFile = new File(fileUrl);
            if (deleteFile.exists()) {
                deleteFile.delete();

            }

        }
    }
}