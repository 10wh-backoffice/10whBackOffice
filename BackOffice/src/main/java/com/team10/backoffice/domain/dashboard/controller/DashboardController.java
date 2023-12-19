package com.team10.backoffice.domain.dashboard.controller;

import jakarta.annotation.PostConstruct;
import lombok.Value;
import org.springframework.stereotype.Controller;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

@Controller
public class DashboardController {
	private final String bucketName;
	private final AmazonS3Client amazonS3Client;

	private String bucketLocation;

	public DashboardController(
			@Value("${custom.bucket-name}") String bucketName,
			AmazonS3Client amazonS3Client) {
		this.bucketName = bucketName;
		this.amazonS3Client = amazonS3Client;
	}

	@PostConstruct
	public void postConstruct() {
		this.bucketLocation = String.format("https://%s.s3.%s.amazonaws.com",
				bucketName, this.amazonS3Client.getBucketLocation(bucketName));
	}

}
