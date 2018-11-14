package spirit;

public class IThread {
	public static void main(String[] args) {
		one();
		
		two();
	}

	private static void two() {
		Ticket ticket = new Ticket();
		Thread t1 = new Thread(ticket,"window1");
		Thread t2 = new Thread(ticket,"window2");
		Thread t3 = new Thread(ticket,"window3");
		
		t1.start();
		t2.start();
		t3.start();
	}

	private static void one() {
		MyThread myThread = new MyThread("one");
		System.out.println(myThread.getName());
		System.out.println("线程创建完毕"+myThread.getState());
		myThread.start();
		System.out.println("线程start完毕"+myThread.getState());
	}
}

class MyThread extends Thread{
	
	public MyThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		super.run();
		System.out.println("线程开始");
		
		for(int i=0;i<20;i++) {
			
		}
		
		System.out.println("逻辑结束");
		
	}
}


/*synchronized (锁对象) {

}
1.可以是任意类型
2.互斥的线程需要同一把锁*/
class Ticket implements Runnable{

	int ticket = 100;
	Object object = new Object();
	@Override
	public void run() {
		while (true) {
			synchronized (object) {
				if (ticket>0) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String name = Thread.currentThread().getName();
					System.out.println(name+"正在卖"+ticket--);
				}
			}
		}
		
	}
	
}
