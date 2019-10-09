package com.donkey.dsa_thu.stack;

public interface DonkeyStack {
	
	int getSize();
	
	boolean isEmpty();
	
	Object top();
	
	void push(Object o);
	
	Object pop();
}
