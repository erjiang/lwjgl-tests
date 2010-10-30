package com.ericjiang.threespace;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Triangle stores three Vertex in counter-clockwise order
 * 
 * @author erjiang
 * 
 */
public class Triangle implements Iterable<Vertex>, Serializable {
	private static final long serialVersionUID = -9064243663843682452L;

	int vCount;
	Vertex[] vertices;

	public Triangle() {
		vertices = new Vertex[3];
		vCount = 0;
	}

	/**
	 * Initialize Triangle with 3 new vertices
	 * 
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public Triangle(Vertex v1, Vertex v2, Vertex v3) {
		vertices = new Vertex[3];
		vertices[0] = v1;
		vertices[1] = v2;
		vertices[2] = v3;
		vCount = 3;
	}
	
	/**
	 * Takes 3 Strings representing floats (Float.parseFloat) and
	 * creates a Vertex out of them.
	 * @param x
	 * @param y
	 * @param z
	 */
	public void addVertex(Vertex v) {
		if(vCount < 3) {
			vertices[vCount] = v;
			vCount++;
		} else
			throw new NoSuchElementException("Too many vertices in triangle!");
	}

	public boolean isComplete() {
		return (vCount == 3);
	}

	@Override
	public TriangleIterator iterator() {
		return new TriangleIterator();
	}

	public class TriangleIterator implements Iterator<Vertex> {
		int index;

		TriangleIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return (index <= 2);
		}

		@Override
		public Vertex next() {
			index++;
			return vertices[index - 1];
		}

		/**
		 * Can't remove a vertex from a triangle!
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Swaps verts 1 and 3 so that the normal is flipped
	 */
	public void flip() {
		Vertex temp = vertices[0];
		vertices[0] = vertices[2];
		vertices[2] = temp;
	}

}
