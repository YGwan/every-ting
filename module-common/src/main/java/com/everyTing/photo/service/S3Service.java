package com.everyTing.photo.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.everyTing.core.exception.TingServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.everyTing.photo.errorCode.PhotoServerErrorCode.PSER_001;

@Service
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${s3.file.name.separator}")
    private String separator;

    private final AmazonS3Client amazonS3Client;

    public S3Service(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadPhoto(Long memberId, MultipartFile multipartFile) {
        final String originalFilename = multipartFile.getOriginalFilename();
        final String s3Filename = memberId + separator + originalFilename;
        final var metadata = getObjectMetadata(multipartFile);

        try {
            amazonS3Client.putObject(bucket, s3Filename, multipartFile.getInputStream(), metadata);
            return amazonS3Client.getUrl(bucket, s3Filename).toString();
        } catch (Exception e) {
            throw new TingServerException(PSER_001);
        }
    }

    private ObjectMetadata getObjectMetadata(MultipartFile multipartFile) {
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        return metadata;
    }
}
