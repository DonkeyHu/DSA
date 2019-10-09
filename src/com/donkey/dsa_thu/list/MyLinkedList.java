package com.donkey.dsa_thu.list;

public class MyLinkedList {
	private int size;
	private ListNode header;
	private ListNode trailer;
	
	public void init() {
		header = new ListNode();
		trailer = new ListNode();
		header.setSucc(trailer);
		header.setPred(null);
		trailer.setSucc(null);
		trailer.setPred(header);
		size = 0;
	}
	
	public ListNode first() {
		return header.getSucc();
	}
	
	public ListNode last() {
		return trailer.getPred();
	}
	
	public Object getDataByRank(int r) {
		ListNode p = first();
		while(0<r--) {
			p = p.getSucc();
		}
		return p.getData();
	}
	
	public ListNode find(Object e,int n,ListNode p) {
		while(0<n--) {
			p = p.getPred();
			if(e.equals(p.getData())) {
				return p;
			}
		}
		return null;
	}
	
	public ListNode insertAsFirst(Object e) {
		size++;
		return header.insertAsSucc(e);
	}
	
	public ListNode insertAsLast(Object e) {
		size++;
		return trailer.insertAsPred(e);
	}
	
	public ListNode insertA(Object e,ListNode p) {
		size++;
		return p.insertAsSucc(e);
	}
	
	public ListNode insertB(Object e,ListNode p) {
		size++;
		return p.insertAsPred(e);
	}
}
