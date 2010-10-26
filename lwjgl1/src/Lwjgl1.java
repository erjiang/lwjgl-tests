import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
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

		
		// wait for close
		for (;;) {
			render();
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				break;
			}
			if (Display.isCloseRequested()) { // Exit if window is closed
				break;
			}
		}
	}

	public static void initGfx() throws LWJGLException {
		
		// below copied from Prototyp http://www.fabiensanglard.net/Prototyp/index.php
		
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

		//GLU.gluOrtho2D(-(int) SCREEN_WIDTH / 2, (int) SCREEN_WIDTH / 2,
		//		(int) -SCREEN_HEIGHT / 2, (int) SCREEN_HEIGHT / 2);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}
	
	public static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glTranslatef(-2.0f, 0.0f, 0.0f);
		GL11.glBegin(GL11.GL_TRIANGLES);
			GL11.glVertex3f(0.0f, 1.5f, 0.0f);
			GL11.glVertex3f(0.0f, 0.0f, 0.0f);
			GL11.glVertex3f(1.0f, 0.0f, 0.0f);
		GL11.glEnd();
		GL11.glTranslatef(3.0f, 0.0f, 0.0f);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex3f(0.0f, 1.5f, 0.0f);
			GL11.glVertex3f(0.0f, 0.0f, 0.0f);
			GL11.glVertex3f(1.0f, 0.0f, 0.0f);
			GL11.glVertex3f(1.0f, 1.2f, 0.0f);
		GL11.glEnd();
	}
}
