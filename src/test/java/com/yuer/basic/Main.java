package com.yuer.basic;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		Class cache = Integer.class.getDeclaredClasses()[0];
		Field c = cache.getDeclaredField("cache");
		c.setAccessible(true);
		Integer[] array = (Integer[]) c.get(cache);
		array[132] = array[133];

		System.out.println(Arrays.toString(array));
		
//		for (int i = 0; i < array.length; i++) {
//			System.out.printf("%d ", array[i]);
//		}
//		System.out.println();
		System.out.printf("%d", 2 + 2);

	}
}
