package com.yuer.thread;

public class Account2Test {
	public static void main(String[] args) {
		Account2 a = new Account2();
		Account2T1 t1 = new Account2T1(a);
		Account2T2 t2 = new Account2T2(a);

		t1.start();
		t2.start();
	}
}