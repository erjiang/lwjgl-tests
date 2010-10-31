import java.io.IOException;
import java.io.StringReader;

import com.ericjiang.threespace.*;

public class STLParserTest {
	public static void main(String[] args) {
		String cube_corner = "       solid cube_corner\n"+
"          facet normal 0.0 -1.0 0.0\n"+
"            outer loop\n"+
"              vertex 0.0 0.0 0.0\n"+
"              vertex 1.0 0.0 0.0\n"+
"              vertex 0.0 0.0 1.0\n"+
"            endloop\n"+
"          endfacet\n"+
"          facet normal 0.0 0.0 -1.0\n"+
"            outer loop\n"+
"              vertex 0.0 0.0 0.0\n"+
"              vertex 0.0 1.0 0.0\n"+
"              vertex 1.0 0.0 0.0\n"+
"            endloop\n"+
"          endfacet\n"+
"          facet normal 0.0 0.0 -1.0\n"+
"            outer loop\n"+
"              vertex 0.0 0.0 0.0\n"+
"              vertex 0.0 0.0 1.0\n"+
"              vertex 0.0 1.0 0.0\n"+
"            endloop\n"+
"          endfacet\n"+
"          facet normal 0.577 0.577 0.577\n"+
"            outer loop\n"+
"              vertex 1.0 0.0 0.0\n"+
"              vertex 0.0 1.0 0.0\n"+
"              vertex 0.0 0.0 1.0\n"+
"            endloop\n"+
"          endfacet\n"+
"        endsolid";
		Mesh mesh = null;
		try {
			mesh = STLParser.parse(new StringReader(cube_corner));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (STLException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Loaded "+mesh.size() +" triangles");
	}
}
