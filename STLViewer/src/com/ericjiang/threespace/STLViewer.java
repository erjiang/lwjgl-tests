package com.ericjiang.threespace;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Timer;
import org.lwjgl.util.glu.GLU;

public class STLViewer {
	static public final int SCREEN_WIDTH = 800;
	static public final int SCREEN_HEIGHT = 600;
	static float MOVE_DELTA = 0;

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws LWJGLException 
	 */
	public static void main(String[] args) throws InterruptedException, LWJGLException {
		// TODO Auto-generated method stub
		Timer time = new Timer();
		
		float fpstick = 1.0f / 30.0f; // gives 60fps??

		try {
			initGfx();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not make gfx :(");
			e.printStackTrace();
		}
		
		float lastTime;
		float now = time.getTime();
		float timeDelta;
		float fiveSecsAgo = now;
		float frames = 0;	
        // loop forever
		for(;;) {
			
			if(time.getTime() - fiveSecsAgo > 5f) {
				System.out.println("FPS: "+((float)frames / 5.0f));
				fiveSecsAgo = time.getTime();
				frames = 0;
			}
			
			for(MOVE_DELTA = 0.0f; MOVE_DELTA < 1.0f;) {
                // check if we want to quit
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {	   // Exit if Escape is pressed
                    System.exit(0);
                }
                if(Display.isCloseRequested()) {
                	System.exit(0);
                }
				
				lastTime = now;
				Timer.tick();
				now = time.getTime();
				timeDelta = now - lastTime;
				MOVE_DELTA += timeDelta;
				frames++;
				
				render();
				Display.update();
				
				// sleep until next update
				if(fpstick - timeDelta > 0.005) {
					Thread.sleep((long) ((fpstick - timeDelta) * 1000));
				}
				
			}
		}
	}

	public static void initGfx() throws LWJGLException {
		
		// below copied from Prototyp http://www.fabiensanglard.net/Prototyp/index.php
		
		Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
		Display.create();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
		GL11.glShadeModel(GL11.GL_SMOOTH);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
		GL11.glClearDepth(1.0f); // Depth Buffer Setup
		GL11.glDisable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
		GL11.glEnable(GL11.GL_BLEND);
		//GL11.glDepthMask(false);
		GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
		GL11.glLoadIdentity(); // Reset The Projection Matrix

		GLU.gluPerspective(
				45.0f,
				(float) SCREEN_WIDTH / (float) SCREEN_HEIGHT,
				0.1f,
				100.0f);
		
		//GLU.gluOrtho2D(-(int) SCREEN_WIDTH / 2, (int) SCREEN_WIDTH / 2,
		//		(int) -SCREEN_HEIGHT / 2, (int) SCREEN_HEIGHT / 2);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}
	
	public static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		// move back into the screen so we can see stuff
		
		GL11.glTranslatef(-2.0f, 0.0f, -6.0f);
		GL11.glRotatef(MOVE_DELTA * 360f, 0.2f, 1.0f, 0);
		GL11.glBegin(GL11.GL_TRIANGLES);
			GL11.glColor3f(1.0f, 0, 0);
			GL11.glVertex3f(0.0f, 1.5f, 0.0f);
			GL11.glColor3f(0, 1.0f, 0);
			GL11.glVertex3f(0.0f, 0.0f, 0.0f);
			GL11.glColor3f(0f, 0, 1.0f);
			GL11.glVertex3f(1.0f, 0.0f, 0.0f);
		GL11.glEnd();
		GL11.glTranslatef(2.0f + MOVE_DELTA, 0.0f, 0.0f);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex3f(0.0f, 1.5f, 0.0f);
			GL11.glVertex3f(0.0f, 0.0f, 0.0f);
			GL11.glVertex3f(1.0f, 0.0f, 0.0f);
			GL11.glVertex3f(1.0f, 1.2f, 0.0f);
		GL11.glEnd();
	}
}
