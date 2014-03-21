package com.yuer.thread;

public class Account2T2 extends Thread {
	private Account2 account;

	public Account2T2(Account2 account) {
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