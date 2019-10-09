package com.donkey.dsa_thu.list;
/**
 * 列表,java版本链表
 * @author DonkeyHu
 * @date 2019-07-23
 */
public class ListNode {
	private Object data;
	private ListNode pred;
	private ListNode succ;
	public ListNode() {
		super();
	}
	public ListNode(Object data, ListNode pred, ListNode succ) {
		super();
		this.data = data;
		this.pred = pred;
		this.succ = succ;
	}
	
	public ListNode insertAsPred(Object e) {
		ListNode x = new ListNode(e,pred,this);
		pred.setSucc(x);
		this.pred = x;
		return x;
	}
	
	public ListNode insertAsSucc(Object e) {
		ListNode x = new ListNode(e,this,succ);
		succ.setPred(x);
		this.succ = x;
		return x;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ListNode getPred() {
		return pred;
	}
	public void setPred(ListNode pred) {
		this.pred = pred;
	}
	public ListNode getSucc() {
		return succ;
	}
	public void setSucc(ListNode succ) {
		this.succ = succ;
	}
}
