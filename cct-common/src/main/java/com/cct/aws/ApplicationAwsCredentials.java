package com.cct.aws;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.auth.AWSCredentials;

public class ApplicationAwsCredentials implements AWSCredentials {

	/** The aws access key id. */
	@Value("${aws.access.key}")
	private String awsAccessKey;

	/** The aws secret key. */
	@Value("${aws.secret.key}")
	private String awsSecretKey;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amazonaws.auth.AWSCredentials#getAWSAccessKeyId()
	 */
	@Override
	public String getAWSAccessKeyId() {
		return awsAccessKey;
	}

	@Override
	public String getAWSSecretKey() {
		// TODO Auto-generated method stub
		return awsSecretKey;
	}

}
