package com.donkey.dsa_thu.stack;

public class DonkeyStackEmptyException extends RuntimeException{

	private static final long serialVersionUID = 5468544716329779848L;
	
	public DonkeyStackEmptyException(String errMsg) {
		super(errMsg);
	}
}
