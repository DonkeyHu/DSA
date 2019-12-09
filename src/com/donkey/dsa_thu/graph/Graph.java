package com.donkey.dsa_thu.graph;

import java.util.Iterator;

import javax.swing.text.Position;
/**
 * @author DonkeyHu
 * @date 2019-10-13
 */
public interface Graph {
	int vNum();
	int eNum();
	
	Iterator vertices();
	Iterator vPositions();
	
	Iterator edges();
	Iterator ePositions();
	
	//是否存在边由顶点u指向v
	boolean areAdjacent(Vertex u,Vertex v);
	//获取由u到v的边，无，则返回null
	Edge edgeFromTo(Vertex u,Vertex v);
	
	Vertex remove(Vertex v);
	Edge remove(Edge e);
	
	Position insert(Vertex v);
	Position insert(Edge e);
	
}
