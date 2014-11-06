package com.yuer.hessiandemo;

import java.io.Serializable;

public class Foo implements Serializable {

	private String name;

	private int x;

	public Foo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return "Foo [name=" + name + ", x=" + x + "]";
	}
}
