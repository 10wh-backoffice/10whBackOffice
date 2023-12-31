package com.team10.backoffice.etc.amazon;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.team10.backoffice.etc.utils.MultipartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
@ConditionalOnProperty(prefix = "cloud.aws.s3", name = "bucket")
@RequiredArgsConstructor
public class AmazonS3ResourceStorage {
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
//	private final AmazonS3Client amazonS3Client;

//	public AmazonS3ResourceStorage( ) {
//		this.amazonS3 = AmazonS3ClientBuilder.standard().withRegion( Regions.AP_NORTHEAST_2 ).build();
//	}

	public void store( String fullPath, MultipartFile multipartFile ) {
//		File file = new File( MultipartUtil.getLocalHomeDirectory(), fullPath);
//		try {
//			multipartFile.transferTo(file);
//			amazonS3Client.putObject(new PutObjectRequest(bucket, fullPath, file)
//					.withCannedAcl( CannedAccessControlList.PublicRead));
//		} catch (Exception e) {
//			throw new RuntimeException( e.getMessage() );
//		} finally {
//			if (file.exists()) {
//				file.delete();
//			}
//		}
	}
}
