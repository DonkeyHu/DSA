package com.donkey.dsa_thu.vector;

public class DonkeyVectorArrayImpl implements DonkeyVector{
	
	private int CAPACITY = 1024;
	private int size = 0; // 实际容量大小
	private Object[] o;

	
	@Override
	public int getSize() {
		return size;
	}
	
	public DonkeyVectorArrayImpl() {
		o = new Object[CAPACITY];
		size = 0;
	}
	
	private void expand() {
		if(size>=CAPACITY) {
			Object[] p = new Object[CAPACITY<<=1];
			for(int i=0;i<size;i++) {
				p[i] = o[i];
			}
			this.o = p;
		}
	}
	
	@Override
	public Object insertAtRank(int rank, Object obj) {
		if(rank < 0 || rank > size) {
			throw new DonkeyBoundaryException("cross the boundary");
		}
		expand();
		for(int i=size;i>rank;i--) {
			o[i] = o[i-1];
		}
		o[rank] = obj;
		size++;
		return obj;
	}

	@Override
	public Object removeAtRank(int rank) {
		if(rank < 0 || rank >= size) {
			throw new DonkeyBoundaryException("cross the boundary");
		}
		Object bak = o[rank];
		for(int i= rank; i<size-1 ; i++) {
			o[i] = o[i+1];
		}
		size -- ;
		return bak;
	}

	@Override
	public Object updateAtRank(int rank, Object obj) {
		if(rank < 0 || rank >= size) {
			throw new DonkeyBoundaryException("cross the boundary");
		}
		Object bak = o[rank];
		o[rank] = obj;
		return bak;
	}

	@Override
	public Object getAtRank(int rank) {
		if(rank < 0 || rank >= size) {
			throw new DonkeyBoundaryException("cross the boundary");
		}
		return o[rank];
	}

	// 需符合判等器的obj
	public int find (Object obj,int lo,int hi){
		while ((lo < hi--) && (obj != o[hi]));
		return hi;
	}

	// 无序向量去重
	public int deduplicate(){
		int oldSize = size;
		int i = 1;
		while (i < size){
			if ((find(o[i], 0, i)) < 0) {
				i++;
			} else {
				removeAtRank(i);
			}
		}
		return oldSize - size;
	}

	// 有序向量去重
	public int unique(){
		int i = 0 , j = 0;
		while (++j < size){
			if (o[i] != o[j]){
				o[++i] = o[j];
			}
		}
		size = ++i;
		return j-i;
	}

	// 有序向量：二分查找版本A
	public static int binSearchA(int[] obj,int e,int lo,int hi){
		while (lo < hi){
			int mi = (lo+hi) >> 1;
			if (e < obj[mi]){
				hi = mi;
			}else if (obj[mi] < e){
				lo = mi + 1;
			}else {
				return mi;
			}
		}
		return -1;
	}

	// 有序向量：二分查找版本B
	public static int binSearchB(int[] obj,int e,int lo,int hi){
		while (1<hi-lo){
			int mi = (lo + hi)>>1;
			if (e < obj[mi]){
				hi = mi;
			}else {
				lo = mi;
			}
		}
		return (obj[lo] == e) ? lo : -1;
	}

	// 有序向量：二分查找版本C
	public static int binSearchC(int[] obj,int e,int lo,int hi){
		while (lo<hi){
			int mi = (lo+hi)>>1;
			if (e<obj[mi]){
				hi = mi;
			}else {
				lo = mi+1;
			}
		}
		return --lo;
	}

	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,8,11,22,22,22,25,28};
		System.out.println(binSearchC(a,22,0,14));
	}
}
