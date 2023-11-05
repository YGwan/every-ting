package com.everyTing.member.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    public S3Service(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public void uploadPhoto(MultipartFile multipartFile) {
        final String originalFilename = multipartFile.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        try {
            amazonS3Client.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
            String image = amazonS3Client.getUrl(bucket, originalFilename).toString();
            System.out.println(image);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
