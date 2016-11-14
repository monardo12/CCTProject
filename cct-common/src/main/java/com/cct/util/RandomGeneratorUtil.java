package com.cct.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RandomGeneratorUtil {

	private static final String DICTIONARY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890123456789";

	/**
	 * Class logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RandomGeneratorUtil.class.getName());

	/**
	 * Secure Random
	 */
	private static SecureRandom secureRandom = null;

	/**
	 * the encryption algorithm
	 */
	private static final String ENCRYPTION_ALGORITHM = "SHA1PRNG";

	/**
	 * Merchant API Key length
	 */
	private static final int LENGTH_API_KEY = 26;

	/**
	 * Merchant API Login length
	 */
	private static final int LENGTH_API_LOGIN = 15;

	/**
	 * Numeric pattern definition
	 */
	private static final Pattern patternNumeric = Pattern.compile("[0-9]+");

	/**
	 * Lowercase letters pattern definition
	 */
	private static final Pattern patternLetters = Pattern.compile("[a-z]+");

	/**
	 * Upercase letters pattern definition
	 */
	private static final Pattern patternLettersUpper = Pattern.compile("[A-Z]+");

	static {
		try {
			secureRandom = SecureRandom.getInstance(ENCRYPTION_ALGORITHM);
		}
		catch (final NoSuchAlgorithmException e) {
			LOGGER.error("The encryption algorithm was not found");
			secureRandom = new SecureRandom();
		}
		catch (final Exception e) {
			LOGGER.error("erroneous initialization of the class");
			secureRandom = new SecureRandom();
		}
	}

	/**
	 * The default constructor.
	 */
	private RandomGeneratorUtil() {

	}

	/**
	 * Generate a Secure Random value for Api key.
	 *
	 * @return String containing the pseudo-random bits.
	 */
	public static String generateApiKey() {

		return generateKey(LENGTH_API_KEY);
	}

	/**
	 * Generate a Secure Random value for API login.
	 *
	 * @return String containing the pseudo-random bits.
	 */
	public static String generateApiLogin() {

		return generateKey(LENGTH_API_LOGIN);
	}

	/**
	 * Generate a Secure Random value for given length.
	 * 
	 * @param length the given length
	 * @return String containing the pseudo-random bits.
	 */
	private static String generateKey(final int length) {

		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append(nextChar());
		}
		if (!isValidKey(result.toString())) {
			return generateKey(length);
		}
		return result.toString();
	}

	/**
	 * Return a random char
	 * 
	 * @return a char
	 */
	private static char nextChar() {

		return DICTIONARY.charAt(secureRandom.nextInt(DICTIONARY.length()));
	}

	/**
	 * Validate if a key is valid
	 * 
	 * @param key the value to be analyzed
	 * @return
	 */
	private static boolean isValidKey(final String key) {

		if (!StringUtils.isEmpty(key)) {
			return patternNumeric.matcher(key).find()
					&& patternLetters.matcher(key).find()
					&& patternLettersUpper.matcher(key).find();
		}
		return false;

	}
}

