package com.ericjiang.threespace;

import java.io.Serializable;

/*
 * Vertex
 * Stores three floats
 */
public class Vertex implements Serializable {
	private static final long serialVersionUID = -7061529723213282194L;
	
	public float x, y, z;

	public Vertex(float x, float y, float z)  {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vertex(String x, String y, String z) {
		this(Float.parseFloat(x),
				Float.parseFloat(y),
				Float.parseFloat(z));
	}

}
