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
		System.out.println("�̴߳������"+myThread.getState());
		myThread.start();
		System.out.println("�߳�start���"+myThread.getState());
	}
}

class MyThread extends Thread{
	
	public MyThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		super.run();
		System.out.println("�߳̿�ʼ");
		
		for(int i=0;i<20;i++) {
			
		}
		
		System.out.println("�߼�����");
		
	}
}


/*synchronized (������) {

}
1.��������������
2.������߳���Ҫͬһ����*/
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
					System.out.println(name+"������"+ticket--);
				}
			}
		}
		
	}
	
}
