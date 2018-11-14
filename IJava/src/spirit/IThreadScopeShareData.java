package spirit;

import java.util.Random;

public class IThreadScopeShareData {
	private static int date = 0;
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				date = new Random().nextInt();
				System.out.println(Thread.currentThread().getName()+" has output date of "+date);
			} 
		}).start();
	}
}
