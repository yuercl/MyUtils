package com.yuer.thread;

public class T1 extends Thread {
	private Account account;

	public T1(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		account.getMoney("Tom", 2000);
	}

}