package lianxi2;

class daa implements Runnable {
	public void run() {
		for (int i = 0; i < 1000000; i++) {
			System.out.println("xxxxxxxxx");
		}
	}
}

public class xiancheng {

	public static void main(String[] args) {
		daa d = new daa();
		new Thread(d).start();
		for (int j = 0; j < 1000000; j++) {
			System.out.println("==================================================");
		}
	}

}
