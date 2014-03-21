package com.yuer.test;

class Base {
	static int i = 1;
	{
		System.out.println("父类构造块1");
	}
	static Base base1 = new Base("static base1");
	static {
		System.out.println("父类静态构造块1");
	}

	Base() {
		System.out.println(i++ + "父类默认构造函数");
	}

	Base(String str) {
		System.out.println(i++ + "父类带参构造函数:" + str + "  :父类构造完成");
	}

	{
		System.out.println("父类构造块2");
	}
	static Base base2 = new Base("static base2");
	static {
		System.out.println("父类静态构造块2---父类静态字段/块初始化完成");
	}
}

public class Initial extends Base {
	static int k = 0;
	static Initial ini1 = new Initial("static ini1");
	static Initial ini2 = new Initial("static ini2");
	public static int i = print("initializing i-");
	public int j = print("initializing j-");
	{
		System.out.println("子类构造块1");
	}
	static {
		System.out.println("子类静态构造块1");
	}

	Initial() {
		// 如果父类没有定义隐式构造函数，则必须显式调用父类带参构造函数。
		// 如果有，则在子类所有初始化开始之前，先执行父类的初始化并调用父类的隐式构造函数；
		System.out.println(" i： " + i);
	}

	Initial(String s) {
		super(s);// 如果不显式调用？
		System.out.println(s + " i： " + i);
	}

	{
		System.out.println("子类构造块2");
	}

	static Initial ini3 = new Initial("static ini3");
	static {
		System.out.println("子类静态构造块2---子类静态字段/块初始化完成");
	}

	static int print(String s) {
		System.out.println(s + "i: " + ++i + " k： " + k++);
		return i;
	}

	public static void main(String[] args) {
		new Initial("Init in main");
	}
}
