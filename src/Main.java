
public class Main implements Runnable {
	
	GameClass oyun = new GameClass();
	
	public static void main(String[] args) {
		(new Thread(new Main())).start();
	}

	@Override
	public void run() {
		while(true) {
			oyun.doIt();
			oyun.winCheck();
		}
	}

}
