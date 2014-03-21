package com.yuer.thread;

public class Account {
	private double money = 3000;

	/**
	 * add sychronized for muti thread getMoney
	 * 
	 * @param name
	 * @param money
	 */
	public synchronized void getMoney(String name, double money) {
		if (money < this.money) {
			this.money = this.money - money;
			System.out.println(name + " 取款 " + money + "元");
		} else {
			System.out.println("余额不足");
		}
	}
}