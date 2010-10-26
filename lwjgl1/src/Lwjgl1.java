import org.lwjgl.util.Timer;

public class Lwjgl1 {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Timer time = new Timer();

		int i = 0;
		float oldTime;

		for (;;) {
			oldTime = time.getTime();
			time.tick();time.tick();time.tick();time.tick();time.tick();time.tick();time.tick();time.tick();time.tick();time.tick();time.tick();time.tick();time.tick();time.tick();
			Thread.sleep(1000);
			System.out.println("It's been " + (time.getTime() - oldTime)
					+ " seconds");
		}
	}
}
