package spirit;

public class ISynchornized {
	public static void main(String[] args) {
		
		Business business = new Business();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=50;i++) {
					business.sub(i);
				}
			}
		}).start();
		
		for(int i=1;i<=50;i++) {
			business.main(i);
		}
	}
}

class Business {
	private boolean bShoudSub = true;
	public synchronized void sub(int i) {
		while (!bShoudSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int j=1;j<=10;j++) {
			System.out.println("sub thread sequece of "+j+",loop of "+i);
		}
		
		bShoudSub = false;
		this.notify();
	}
	
	public synchronized void main(int i) {
		while (bShoudSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int j=1;j<=100;j++) {
			System.out.println("main thread sequece of "+j+",loop of "+i);
		}
		
		bShoudSub = true;
		this.notify();
	}
}
