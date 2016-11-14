package com.cct.util;

import java.security.SecureRandom;

public final class PublicKeyGenerator {
	
	/** Public key prefix*/
	private static final String PK_PREFIX = "PK";
	/** The string 9*/
	private static final int NINE = 9;
	/** The number 13, the alphabet has four 13-element parts */
	private static final int QUADRANT = 13;
	/** The length of the public key */
	private static final int PUBLIC_KEY_LEN = 24;
	/** Random generator */
	private static final SecureRandom RND;
	/** The alphabet*/
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/** The length of the merchant id*/
	private static int lenUsuario = 0;
	/** The length of the length of the merchant id*/
	private static int lenlenUsuario = 0;
	/** A counter: all ints from the merchant added to the public key*/
	private static int countUsuario = 0;
	/** A counter: all ints in the public key*/
	private static int countInt = 0;
	/** A counter: all chars in the public key*/
	private static int countChar = 0;
	/** The SHA-1 Algorithm */
	private static final String SHA1PRNG = "SHA1PRNG";

	/* Constructor should not be called*/
	private PublicKeyGenerator() {
		// not called
	}
	
    //initializes the SecureRandom
	static
	{
		SecureRandom secureRandom;
		try{
			secureRandom = SecureRandom.getInstance(SHA1PRNG);
		}catch(Exception e){
			secureRandom = new SecureRandom();
		}
		RND = secureRandom;
		RND.setSeed(System.nanoTime());
	}
	
	/**
	 * Creates a new public key.
	 * @param usuarioId
	 * @return
	 */
	public static String create(Long usuarioId) {
		// check whether or not the usuarioId has a positive number
		if (usuarioId < 0) {
			return "";
		}
		String usuario = String.valueOf(usuarioId);
		
		lenUsuario = usuario.length();
		lenlenUsuario = String.valueOf(lenUsuario).length();
		countUsuario = 0;
		countInt = 0;
		countChar = 0;
		
		StringBuilder publicKey = new StringBuilder("");
		
		while (publicKey.length() < PUBLIC_KEY_LEN) {
			if (PUBLIC_KEY_LEN - publicKey.length() <= (lenUsuario - countUsuario) + lenlenUsuario) {
				if (countChar == 0) {
					publicKey.append(getNextChar(lenlenUsuario));
					countChar++;
				}
				publicKey.append(getNext(usuario));

			} else {
				if (isNextInteger()) {
					if (countInt > lenUsuario) {
						publicKey.append(String.valueOf(getNextInt()));
					} else {
						publicKey.append(getNext(usuario));
					}
				} else {
					if (countChar == 0) {
						publicKey.append(getNextChar(lenlenUsuario));
						countChar++;
					} else {
						publicKey.append(String.valueOf(getNextChar(0)));
						countChar++;
					}
				}
			}
		}
		return PK_PREFIX + publicKey;
	}

	/**
	 * Gets a random Char from the alphabet. The alphabet is composed of four
	 * quadrants. quadrant = 0: all alphabet. quadrant = 1: "abcdefghijklm".
	 * quadrant = 2: "nopqrstuvwxyz". quadrant = 3: "ABCDEFGHIJKLM". quadrant =
	 * 4: "NOPQRSTUVWXYZ".
	 * 
	 * @param quadrant
	 * @return
	 */
	private static char getNextChar(int quadrant) {
		int rand = (quadrant == 0 || quadrant > 4) ? RND.nextInt(ALPHABET
				.length()) : RND.nextInt(QUADRANT) + (quadrant - 1) * QUADRANT;
		return ALPHABET.charAt(rand);
	}

	/**
	 * Returns a random int from 0 to 9.
	 * @return
	 */
	private static int getNextInt() {
		return RND.nextInt(10);
	}

	/**
	 * Returns a random boolean, 50% chance of true.
	 * @return
	 */
	private static boolean isNextInteger() {
		return  (RND.nextInt() % 2 == 0);
	}

	/**
	 * Gets the next element for the public key
	 * @param usuario
	 * @return
	 */
	private static String getNext(String usuario) {
		String str = "";
		if (countInt == 0) {
			str = String.valueOf(lenUsuario);
		} else {
			if (countUsuario >= lenUsuario) {
				str = String.valueOf(getNextChar(0));
			} else {
				str = String.valueOf(NINE - Integer.parseInt(String.valueOf(usuario.charAt(countUsuario))));
				countUsuario++;
			}
		}
		countInt++;
		return str;
	}
	
}