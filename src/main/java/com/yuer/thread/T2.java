package com.yuer.thread;

public class T2 extends Thread {
	private Account account;

	public T2(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		account.getMoney("Rose", 2000);
	}
}