package com.donkey.dsa_thu.stack;

public class DonkeyStackFullException extends RuntimeException{
	private static final long serialVersionUID = -3082043327876937810L;
	
	public DonkeyStackFullException(String errMsg) {
		super(errMsg);
	}
}
