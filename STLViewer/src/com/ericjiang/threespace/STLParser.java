package com.ericjiang.threespace;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.NoSuchElementException;

public class STLParser {

	enum Mode { TOP, SOLID, FACET, LOOP };

	static LineNumberReader in;
	
	public static Mesh parse(Reader file) throws IOException, STLException {
		in = new LineNumberReader(file);
		Mesh mesh = new Mesh();
		String line;
		Triangle currTri = null;
		Mode mode = Mode.TOP;
		
		while ((line = in.readLine()) != null) {
			line = line.trim();
			if(line.length() == 0) // blank line
				continue;
			String[] fields = line.split(" ");
			
			switch (mode) {
				case TOP:
					if("solid".equals(fields[0])) {
						mode = Mode.SOLID;
					} else errLn(line);
					break;
				case SOLID:
					if("endsolid".equals(fields[0])) {
						mode = Mode.TOP;
					}
					else if("facet".equals(fields[0])) {
						mode = Mode.FACET;
					} else errLn(line);
					break;
				case FACET:
					if("endfacet".equals(fields[0])) {
						mode = Mode.SOLID;
					}
					else if("outer".equals(fields[0]) && "loop".equals(fields[1])) {
						currTri = new Triangle();
						mode = Mode.LOOP;
					} else errLn(line);
					break;
				case LOOP:
					if("endloop".equals(fields[0])) {
						if(!currTri.isComplete())
							throw new STLException("Incomplete triangle ended at line "+in.getLineNumber());
						mesh.addLast(currTri);
						mode = Mode.FACET;
					}
					else if("vertex".equals(fields[0])) {
						try {
							currTri.addVertex(new Vertex(fields[1], fields[2], fields[3]));
						} catch (NoSuchElementException e) {
							throw new STLException("Too many vertices found in Triangle at line "+in.getLineNumber());
						}
					} else errLn(line);
					break;
				default:
					throw new RuntimeException("Unreachable mode " + mode.toString() + " reached??");
					
			}
		}
		
		return mesh;

	}
	
	private static void errLn(String str) {
		System.err.println("Unexpected STL line "+in.getLineNumber()+": ".concat(str));
	}
}
