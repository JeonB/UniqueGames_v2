package com.uniqueGames.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.uniqueGames.fileutil.CommonUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class AwsS3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${file-folder}")
    String fileFolder;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName; //버킷 이름

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        validateFileExists(multipartFile); //파일이 있는지 확인하는 메서드

        String fileName = CommonUtils.buildFileName(multipartFile.getOriginalFilename()); //fileName

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        InputStream inputStream = multipartFile.getInputStream();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        objectMetadata.setContentLength(bytes.length);
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {

            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileFolder+fileName, byteArrayInputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error("Can not upload image, ", e);
            throw new RuntimeException("cannot upload image");
        }
        String url = amazonS3Client.getUrl(bucketName, fileFolder+fileName).toString();

        return url;
    }


    private void validateFileExists(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            throw new RuntimeException("file is empty");
        }
    }

    public void deleteFile(String fileName){
        DeleteObjectRequest request = new DeleteObjectRequest(bucketName,fileFolder+fileName);
        amazonS3Client.deleteObject(request);
    }
}