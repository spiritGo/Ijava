package spirit;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.omg.IOP.TAG_RMI_CUSTOM_MAX_STREAM_FORMAT;

public class CallableAndFuture {
	public static void main(String[] args) {
//		future();
//		completion();

//		lockTest();
//		trycache();
//		semaphoreTest();
//		test();
//		空中网面试题1
//		test01();
//		空中网面试题2
		test02();

	}

	private static void test02() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		Semaphore semaphore = new Semaphore(1);
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						String input = queue.take();
						String output = TestDo.doSome(input);
						System.out.println(Thread.currentThread().getName() + ":" + output);
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}).start();
		}

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 10; i++) {
			String input = i + "";
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class TestDo {
		public static String doSome(String input) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String output = input + ":" + (System.currentTimeMillis() / 1000);
			return output;
		}
	}

	private static void test01() {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(16);
		for (int i = 0; i < 16; i++) {
			try {
				queue.put(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							System.out.println(System.currentTimeMillis() + "," + queue.take() + ","
									+ Thread.currentThread().getName());
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	private static void test() {
		Collection<Integer> list = new CopyOnWriteArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {

			list.remove(1);
			System.out.println("***" + iterator.next());
		}

		for (Integer integer : list) {
			System.out.println(integer);
		}

	}

	private static void semaphoreTest() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(3);
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(
							"线程" + Thread.currentThread().getName() + "进入，当前已有" + (3 - semaphore.availablePermits()));

					try {
						Thread.sleep((long) (Math.random() * 10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
					semaphore.release();
				}
			};

			threadPool.execute(runnable);
		}
	}

//	必需要catch才可以抛异常 
	private static void trycache() {
		try {
			int i = 1 / 0;

		} catch (Exception e) {

		} finally {
			System.out.println("this is finally");
		}

	}

	private static void lockTest() {
		Outer outer = new Outer();
		new Thread(new Runnable() {

			@Override
			public void run() {
				outer.out("Tom");
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				outer.out("Lucy");
			}
		}).start();
	}

	private static void completion() {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return index;
				}
			});
		}

		for (int i = 0; i < 10; i++) {
			try {
				System.out.println(completionService.take().get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	private static void future() {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		Future<String> future = singleThreadExecutor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "hello";
			}
		});
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}

class Outer {
	Lock lock = new ReentrantLock();

	public void out(String name) {
		lock.lock();
		try {
			for (Character c : name.toCharArray()) {
				System.out.println(c);
			}
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
	}
}
