package spirit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IThreadPool {
	public static void main(String[] args) {
		fixThreadPool();
		cacheThreadPool();
		singleThreadPool();
		ScheduledThreadPool();
	}

	private static void ScheduledThreadPool() {
		Executors.newScheduledThreadPool(3).schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("bombing!");
			}
		}, 200, TimeUnit.MILLISECONDS);

	}

	private static void singleThreadPool() {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		for (int j = 0; j < 10; j++) {
			final int task = j;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10; i++) {
						System.out.println(Thread.currentThread().getName() + ", " + i + " for " + task);
					}
				}
			});
		}
		threadPool.shutdown();
	}

	private static void cacheThreadPool() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int j = 0; j < 10; j++) {
			final int task = j;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10; i++) {
						System.out.println(Thread.currentThread().getName() + ", " + i + " for " + task);
					}
				}
			});
		}
		threadPool.shutdown();
	}

	private static void fixThreadPool() {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		for (int j = 0; j < 10; j++) {
			final int task = j;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10; i++) {
						System.out.println(Thread.currentThread().getName() + ", " + i + " for " + task);
					}
				}
			});
		}
		threadPool.shutdown();
	}
}
