package com.donkey.dsa_thu.graph;

import javax.swing.text.Position;

/**
 * @author DonkeyHu
 * @date 2019-10-13
 */
public interface Edge {
	final static int UNKNOWN = 0;
	final static int TREE = 1;
	final static int CROSS = 2;
	final static int FORWARD = 3;
	final static int BACKWARD = 4;
	
	Object getInfo();
	Object setInfo(Object o);
	
	Position getEPosInE();
	Position getVPosInV(int i);
	Position getEPosInI(int i);
	
	int getType();
	int setType();
}
