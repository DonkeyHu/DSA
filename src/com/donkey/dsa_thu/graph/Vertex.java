package com.donkey.dsa_thu.graph;

import java.util.Iterator;

import javax.swing.text.Position;

/**
 * 顶点
 * @author DonkeyHu
 * @date 2019-10-13
 */
public interface Vertex {
	final static int UNDISCOVERED = 0;
	final static int DISCOVERED = 1;
	final static int VISITED = 2;
	
	//返回当前顶点信息
	Object getInfo();
	Object setInfo();
	
	//返回当前顶点的出度入度
	int outDegree();
	int inDegree();
	
	//返回当前顶点所有关联边，以及关联边的位置
	Iterator inEdges();
	Iterator inEdgesPositions();
	Iterator outEdges();
	Iterator outEdgesPositions();
	
	Position getVPosInV();
	
	//读取设置顶点的状态
	int getStatus();
	int setStatus(int s);
	
	//读取设置顶点的时间标签
	int getDStamp();
	int setDStamp(int s);
	int getFStamp();
	int setFStamp(int s);
	
	//读取设置顶点距离起点的最短距离
	int getInstance();
	int setDistance(int s);
	
	Vertex getBFSParent();
	Vertex setBFSParent(Vertex s);
	
}
