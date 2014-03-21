package com.yuer.thread;

import java.util.concurrent.locks.ReentrantLock;

public class Account2 {
	private double money = 3000;
	private ReentrantLock reentrantLock = new ReentrantLock();

	public void getMoney(String name, double money) {
		reentrantLock.lock();
		try {
			if (money < this.money) {
				this.money = this.money - money;
				System.out.println(name + "取款" + money + "元");
			} else {
				System.out.println(name + "余额不足");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reentrantLock.unlock();
		}
	}
}
