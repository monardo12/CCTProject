package com.cct.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cct.model.Cliente;
import com.cct.redis.RedisClient;
import com.cct.repo.ClienteRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

@Controller
public class HelloWorldController {

	/**
	 * String to hold name of the encryption algorithm.
	 */
	public static final String ALGORITHM = "RSA";

	/**
	 * String to hold the name of the private key file.
	 */
	public static final String PRIVATE_KEY_FILE = "private.key";

	/**
	 * String to hold name of the public key file.
	 */
	public static final String PUBLIC_KEY_FILE = "public.key";

	@Autowired
	private RedisClient redisClient;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping("/test")
	@ResponseBody
	public String helloWorld(){
		Object obj = redisClient.get("key");
		if(obj == null){
			redisClient.put("key", "1");
		} else {
			int value = Integer.parseInt((String) obj);
			value++;
			redisClient.put("key", Integer.toString(value));
		}

		int realValue = Integer.parseInt((String) redisClient.get("key"));

		return "Value: " + realValue;
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public Cliente prueba(){
		return clienteRepository.findOne(1L);
	}

	@RequestMapping("/loaderio-0d6da71e28d78fae023635643fd2e089")
	@ResponseBody
	public String loaderIO() {
		return "loaderio-0d6da71e28d78fae023635643fd2e089";
	}
	
	@RequestMapping("/loaderio-dc44d0b7c768d24ed2d9376d5ff1594b")
	@ResponseBody
	public String loaderIOCarlos() {
		return "loaderio-dc44d0b7c768d24ed2d9376d5ff1594b";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	@ResponseBody
	public String form(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap();
		StringBuilder response = new StringBuilder("Recibido: ");
		for(Map.Entry<String, String[]> param : params.entrySet()){
			response.append(param.getKey())
			.append(":")
			.append(Arrays.toString(param.getValue()))
			.append(",");
		}
		return response.toString();
	}

	@RequestMapping("/pruebaEncriptada")
	@ResponseBody
	public String pruebaEncriptada() {

		String originalText = "loaderio-dc44d0b7c768d24ed2d9376d5ff1594b";

		try {

			ObjectInputStream inputStream = null;

			// Encrypt the string using the public key
			inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
			final PublicKey publicKey = (PublicKey) inputStream.readObject();
			final byte[] cipherText = encrypt(originalText, publicKey);

			// Decrypt the cipher text using the private key.
			inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
			final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
			final String plainText = decrypt(cipherText, privateKey);

			// Printing the Original, Encrypted and Decrypted Text
			return "Original: " + originalText + "<br>Encrypted: " + cipherText.toString() + "<br>Decrypted: " + plainText;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Error";
	}

	/**
	 * Encrypt the plain text using public key.
	 *
	 * @param text
	 *          : original plain text
	 * @param key
	 *          :The public key
	 * @return Encrypted text
	 * @throws java.lang.Exception
	 */
	public static byte[] encrypt(String text, PublicKey key) {
		byte[] cipherText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			// encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(text.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	/**
	 * Decrypt text using private key.
	 *
	 * @param text
	 *          :encrypted text
	 * @param key
	 *          :The private key
	 * @return plain text
	 * @throws java.lang.Exception
	 */
	public static String decrypt(byte[] text, PrivateKey key) {
		byte[] dectyptedText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance(ALGORITHM);

			// decrypt the text using the private key
			cipher.init(Cipher.DECRYPT_MODE, key);
			dectyptedText = cipher.doFinal(text);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(dectyptedText);
	}
}
