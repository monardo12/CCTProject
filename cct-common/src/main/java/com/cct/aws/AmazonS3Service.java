package com.cct.aws;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cct.dto.ReporteDTO;

@Service
public class AmazonS3Service {

	private static final Logger LOGGER = LoggerFactory.getLogger(AmazonS3Service.class);

	private AmazonS3Client amazonS3Client;

	private static final String AWS_BUCKET_NAME_ENV_VAR = "AWS_BUCKET_NAME";

	private static final String UPLOADED_FILE_FORMAT = "https://s3.amazonaws.com/%s/%s/%s";

	private static final String REPORT_NAME_FORMAT = "reporte_%s.pdf";

	private static final String REPORTS_FOLDER = "reportes";

	/**
	 * Instantiates a new amazon S3 service.
	 */
	public AmazonS3Service() {
		amazonS3Client = new AmazonS3Client(new EnvironmentVariableCredentialsProvider());
	}

	/**
	 * Upload file to amazon S3 Bucket
	 *
	 * @param fileName the file name
	 * @param bytes the bytes
	 */
	public String uploadFile(String fileName, String folder, byte[] bytes){
		final String awsBucketName = System.getenv(AWS_BUCKET_NAME_ENV_VAR);
		InputStream stream = new ByteArrayInputStream(bytes);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(bytes.length);

		try {
			byte[] resultByte; resultByte = DigestUtils.md5(stream);
			String streamMD5 = new String(Base64.encodeBase64(resultByte));
			metadata.setContentMD5(streamMD5);

			amazonS3Client.putObject(new PutObjectRequest(awsBucketName + "/" + folder, fileName, stream,  metadata).withCannedAcl(CannedAccessControlList.PublicRead));
			return buildFileUrl(fileName, folder);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public String uploadReporte(ReporteDTO reporteDTO, byte[] bytes){
		String fileName = buildReportFileName(reporteDTO);
		return uploadFile(fileName, REPORTS_FOLDER, bytes);
	}

	public String buildReportFileName(ReporteDTO reporteDTO){
		return String.format(REPORT_NAME_FORMAT, reporteDTO.getId());
	}

	public String buildReportUrl(ReporteDTO reporteDTO){
		String fileName = buildReportFileName(reporteDTO);
		return buildFileUrl(fileName, REPORTS_FOLDER);
	}

	private String buildFileUrl(String fileName, String folder){
		final String awsBucketName = System.getenv(AWS_BUCKET_NAME_ENV_VAR);
		return String.format(UPLOADED_FILE_FORMAT, awsBucketName, folder, fileName);
	}

}
