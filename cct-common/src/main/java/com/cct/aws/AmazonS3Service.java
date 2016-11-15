package com.cct.aws;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class AmazonS3Service {

	private static final Logger LOGGER = LoggerFactory.getLogger(AmazonS3Service.class);
	
	private AmazonS3Client amazonS3Client;
	
	private ApplicationAwsCredentials awsCredentials;
	
	@Value("${aws.bucket.name}")
	private String awsBucketName;
	
	/**
	 * Instantiates a new amazon S3 service.
	 */
	@Autowired
	public AmazonS3Service(ApplicationAwsCredentials awsCredentials) {
		this.awsCredentials = awsCredentials;
		amazonS3Client = new AmazonS3Client(awsCredentials);	 
	}
	
	/**
	 * Gets the file upload presigned url.
	 *
	 * @param fileName the file name
	 * @param contentType the content type
	 * @return the file upload url
	 */
	public URL getFileUploadPresignedUrl(String fileName, String contentType){
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
				awsBucketName, buildObjectKey(fileName), HttpMethod.PUT);
		
		generatePresignedUrlRequest.setContentType(contentType);
		             
		return amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
	}
	
	/**
	 * Builds the object key.
	 *
	 * @param fileName the file name
	 * @return the string
	 */
	private String buildObjectKey(String fileName) {
		String objectKey = StringUtils.EMPTY;
		try {
			objectKey = Long.toString(System.currentTimeMillis()).concat(URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName()));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Unsupported encoding", e);
		}
		return objectKey;
	}
	
}
