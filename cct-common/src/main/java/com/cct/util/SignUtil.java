package com.cct.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public final class SignUtil {

	private static final String DEFAULT_ALGORITHM = Algorithm.HMAC_SHA_256;
	
	private static final String HMAC_SHA256 = "HmacSHA256";
	
	public static boolean validateSignature(final String algorithm, final String signature, final String key,
			final String message) {

		final String generatedSignature = createSign(algorithm, key, message);
		return signature.equalsIgnoreCase(generatedSignature);
	}
	
	public static String createSign(final String algorithm, final String key, final String message){
		final String str = key + "~" + message;
		String sign = null;
		
		final String localAlgorithm = StringUtils.isBlank(algorithm) ? DEFAULT_ALGORITHM : algorithm;
		if (Algorithm.MD5.equalsIgnoreCase(localAlgorithm)) {
			sign = DigestUtils.md5Hex(str);
		}
		else if (Algorithm.SHA_256.equalsIgnoreCase(localAlgorithm)) {
			sign = DigestUtils.sha256Hex(str);
		}
		else if (Algorithm.HMAC_SHA_256.equalsIgnoreCase(localAlgorithm)) {
			sign = encryptWithHmacSha256(str, key);
		}
		else {
			throw new IllegalArgumentException("Could not create signature. Invalid algorithm " + localAlgorithm);
		}
		return sign;
	}
	
	public static String encryptWithHmacSha256(final String message, final String key) {
		try {
			Mac sha256Hmac = Mac.getInstance(HMAC_SHA256);
			final SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
			sha256Hmac.init(secretKey);
			return Hex.encodeHexString(sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8)));
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			return null;
		}
	}
	
}
