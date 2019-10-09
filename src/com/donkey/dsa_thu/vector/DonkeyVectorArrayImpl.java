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

}
