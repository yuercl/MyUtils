package com.yuer.thread;

public class AccountTest {
	public static void main(String[] args) {
		Account a = new Account();
		T1 t1 = new T1(a);
		T2 t2 = new T2(a);

		t1.start();
		t2.start();
	}
}