package com.donkey.dsa_thu.vector;

public interface DonkeyVector {
	
	int getSize();
	
	Object getAtRank(int rank);
	
	Object insertAtRank(int rank,Object obj);
	
	Object removeAtRank(int rank);
	
	Object updateAtRank(int rank,Object obj);
}
