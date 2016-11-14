package com.cct.security.basic;

public class BasicCredentials {

	private String privateKey;
	
	private String privateLogin;

	public BasicCredentials(String privateKey, String privateLogin) {
		this.privateKey = privateKey;
		this.privateLogin = privateLogin;
	}

	public String getPrivateLogin() {
		return privateLogin;
	}

	public void setPrivateLogin(String privateLogin) {
		this.privateLogin = privateLogin;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
}
