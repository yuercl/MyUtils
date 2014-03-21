package com.yuer.campare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class Main {
	/**
	 * @param args
	 */
	@Test
	public void main() {
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new User(2));
		userList.add(new User(-1));
		userList.add(new User(23));
		userList.add(new User(4));
		userList.add(new User(3));
		userList.add(new User(3));
		userList.add(new User(1));
		User[] users = (User[]) userList.toArray(new User[1]);

		UserCompare<User> userCompare = new UserCompare<User>();
		Arrays.sort(users, userCompare);

		System.out.println(users[0].toString());
		System.out.println(users[users.length - 1].toString());
	}
}

class UserCompare<T> implements Comparator<T> {
	public int compare(T o1, T o2) {
		return ((User) o1).getId() - ((User) o2).getId();
	}
}
