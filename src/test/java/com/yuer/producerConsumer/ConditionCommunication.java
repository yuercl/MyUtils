package com.yuer.producerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {

	public static void main(String[] args) {
		final Business business = new Business();

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					business.sub2(i);
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					business.sub3(i);
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					business.main(i);
				}
			}
		}).start();

	}

	static class Business {

		private int shouldSub = 1;
		private Lock lock = new ReentrantLock();

		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();

		public void sub2(int i) {
			try {
				lock.lock();
				while (shouldSub != 2) {
					try {
						// this.wait();
						condition2.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub2 thread sequence is " + j + " loop of " + i);
				}
				shouldSub = 3;
				// this.notify();
				condition3.signal();
			} finally {
				lock.unlock();
			}
		}

		public void sub3(int i) {
			try {
				lock.lock();
				while (shouldSub != 3) {
					try {
						// this.wait();
						condition3.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 20; j++) {
					System.out.println("sub3 thread sequence is " + j + " loop of " + i);
				}
				shouldSub = 1;
				// this.notify();
				condition1.signal();
			} finally {
				lock.unlock();
			}
		}

		public void main(int i) {
			try {
				lock.lock();
				while (shouldSub != 1) {
					try {
						// this.wait();
						condition1.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread sequence is " + j + " loop of " + i);
				}
				shouldSub = 2;
				// this.notify();
				condition2.signal();
			} finally {
				lock.unlock();
			}
		}
	}

}