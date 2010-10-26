import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Timer;
import org.lwjgl.util.glu.GLU;

public class Lwjgl1 {
	static public final int SCREEN_WIDTH = 800;
	static public final int SCREEN_HEIGHT = 600; // 480
	private static final boolean FULL_SCREEN = false;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Timer time = new Timer();

		try {
			initGfx();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not make gfx :(");
			e.printStackTrace();
		}
		
		Thread.sleep(1000);
	}

	public static void initGfx() throws LWJGLException {
		
		Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
		Display.create();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
		GL11.glClearDepth(1.0f); // Depth Buffer Setup
		GL11.glDisable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
		GL11.glLoadIdentity(); // Reset The Projection Matrix

		GLU.gluOrtho2D(-(int) SCREEN_WIDTH / 2, (int) SCREEN_WIDTH / 2,
				(int) -SCREEN_HEIGHT / 2, (int) SCREEN_HEIGHT / 2);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}
}
