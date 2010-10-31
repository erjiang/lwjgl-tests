package com.ericjiang.threespace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class STLParser {

	enum Mode { TOP, SOLID, FACET, LOOP };

	public static Mesh parse(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		Mesh mesh = new Mesh();
		String line;
		Triangle currTri;
		Mode mode = Mode.TOP;
		
		while ((line = in.readLine()) != null) {
			line = line.trim();
			String[] fields = line.split(" ");
			switch (mode) {
			case TOP:
				if("solid".equals(fields[0])) {
					mode = Mode.SOLID;
					break;
				} else {
					errLn(line);
				}
				break;
			case FACET:
				if("outer".equals(fields[0]) && "loop".equals(fields[1])) {
					currTri = new Triangle();
					mode = Mode.LOOP;
				} else errLn(line);
				break;
			}
			/// TODO: more modes
		}
		
		return mesh;

	}
	
	private static void errLn(String str) {
		System.err.println("Unexpected STL line: ".concat(str));
	}
}
