package com.yuer.campare;

import java.io.Serializable;

public class User implements Serializable, Comparable<User> {

	private static final long serialVersionUID = 6136267477978211560L;

	private int id;

	private String name;
	private String userName;
	private String pwd;
	private int age;

	public User(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String toString() {
		return "id=" + id;
	}

	public int compareTo(User o) {
		return this.id - o.id;
	}

}
