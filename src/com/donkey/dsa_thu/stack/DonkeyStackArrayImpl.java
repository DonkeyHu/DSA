package com.donkey.dsa_thu.stack;

public class DonkeyStackArrayImpl implements DonkeyStack {
	
	private final static int CAPACITY = 1024;
	private int capacity;
	private int top = -1;// 栈顶的位置元素
	private Object[] obj;
	
	public DonkeyStackArrayImpl() {
		this(CAPACITY);
	}
	
	public DonkeyStackArrayImpl(int cab) {
		this.capacity = cab;
		obj = new Object[capacity];
	}

	@Override
	public int getSize() {
		return top+1;
	}

	@Override
	public boolean isEmpty() {
		return top<0?true:false;
	}

	@Override
	public Object top() {
		if(isEmpty()) {
			throw new DonkeyStackEmptyException("the stack is empty");
		}
		return obj[top];
	}

	@Override
	public void push(Object o) {
		if(getSize() == capacity) {
			throw new DonkeyStackFullException("the stack size is full");
		}
		obj[++top] = o;
	}

	@Override
	public Object pop() {
		if(isEmpty()) {
			throw new DonkeyStackEmptyException("the stack is empty");
		}
		Object elem = obj[top];
		obj[top--] = null;
		return elem;
	}

}
