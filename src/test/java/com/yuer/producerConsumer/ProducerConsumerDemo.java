package com.yuer.producerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用Lock、Condition实现生产者消费者模式
 */
public class ProducerConsumerDemo {

	public static void main(String[] args) {
		int producerCount = 10;
		int consumerCount = 15;

		final ProducerConsumerDemo pcd = new ProducerConsumerDemo(5); // 缓冲区大小为5

		Thread[] producerThreads = new Thread[producerCount];
		for (int i = 0; i < producerCount; i++) {
			producerThreads[i] = new Thread("producer" + (i + 1)) {

				@Override
				public void run() {
					pcd.produce();
				}
			};
		}

		Thread[] consumerThreads = new Thread[consumerCount];
		for (int j = 0; j < consumerCount; j++) {
			consumerThreads[j] = new Thread("consumer" + (j + 1)) {
				@Override
				public void run() {
					pcd.consume();
				}
			};
		}

		// 启动生产者消费者线程
		for (int i = 0; i < producerCount; i++) {
			producerThreads[i].start();
		}
		for (int j = 0; j < consumerCount; j++) {
			consumerThreads[j].start();
		}
	}

	private static final int DEFAULT_BUFFER_SIZE = 10;
	private int bufferSize; // 缓冲区大小
	private List<Object> bufferList;

	private final Lock lock = new ReentrantLock(true);
	private final Condition condition = lock.newCondition();

	public ProducerConsumerDemo(int bufferSize) {
		this.bufferSize = bufferSize > 0 ? bufferSize : DEFAULT_BUFFER_SIZE;
		bufferList = new ArrayList<Object>(bufferSize);
	}

	// 生产
	public void produce() {
		lock.lock(); // 加锁
		try {
			while (bufferList.size() == bufferSize) { // 缓冲区满了
				System.out.println("Producer wait, thread: " + Thread.currentThread().getName());
				condition.await();
			}

			// 生产
			bufferList.add(new Object());
			System.out.println("Producer produce one, now buffer size: " + bufferList.size() + ", and thread: " + Thread.currentThread().getName());
			condition.signalAll(); // 通知消费者
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	// 消费
	public void consume() {
		lock.lock(); // 加锁
		try {
			while (bufferList.isEmpty()) { // 缓冲区空了
				System.out.println("Consumer wait, thread: " + Thread.currentThread().getName());
				condition.await();
			}

			// 消费
			bufferList.remove(0); // 从链表头部移除一个
			System.out.println("Consumer consumer one, now buffer size: " + bufferList.size() + ", and thread: " + Thread.currentThread().getName());
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}