package com.yuer.beanutils;

import java.util.Map;
import java.util.HashMap;
import java.util.GregorianCalendar;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtilsExample1 {
	private User prepareData() {
		Profile profile = new Profile();
		profile.setEmail("shiyangxt@126.com");
		profile.setBirthDate(new GregorianCalendar(2013, 10, 30).getTime());
		Map<String, String> phone = new HashMap<String, String>();
		phone.put("home", "11011011");
		phone.put("office", "82826905");
		profile.setPhone(phone);
		Address[] address = { new Address("中国", "北京", "100120", "天安门北大街888号"), new Address("中国", "广州", "100120", "石牌村666号") };
		profile.setAddress(address);

		User user = new User();
		user.setUserId(new Long(123456789));
		user.setUsername("shiyang");
		user.setPassword("12345");
		user.setProfile(profile);
		return user;
	}

	public static void main(String[] args) {
		BeanUtilsExample1 example = new BeanUtilsExample1();
		User user = example.prepareData();
		try {
			System.out.println("输出对象的属性值---------------------------------");
			System.out.println(BeanUtils.getProperty(user, "userId"));
			System.out.println(BeanUtils.getProperty(user, "username"));// 返回字符型
			System.out.println(PropertyUtils.getProperty(user, "username"));// 返回对象类型
			System.out.println(BeanUtils.getProperty(user, "profile.email"));// 重点
			System.out.println(BeanUtils.getProperty(user, "profile.birthDate"));// 重点
			System.out.println(BeanUtils.getProperty(user, "profile.phone(home)"));// 重点
			System.out.println(BeanUtils.getProperty(user, "profile.phone(office)"));// 重点
			System.out.println(BeanUtils.getProperty(user, "profile.address[0].city"));// 重点
			System.out.println(BeanUtils.getProperty(user, "profile.address[1].city"));// 重点

			User user2 = new User();
			BeanUtils.copyProperties(user2, user);
			// 两层拷贝，基本类型复制值，对于引用类型（除String，封装类型外）复制地址值。
			System.out.println("输出复制属性的属性值-------------------------------");
			System.out.println(BeanUtils.getProperty(user, "username"));
			System.out.println(BeanUtils.getProperty(user, "profile.birthDate"));// 重点
			System.out.println(BeanUtils.getProperty(user, "profile.phone(home)"));// 重点
			System.out.println(BeanUtils.getProperty(user, "profile.address[0].addr"));// 重点

			System.out.println("输出复制属性修改以后的属性值---------------------");
			BeanUtils.setProperty(user2, "userId", new Long(8888888));
			PropertyUtils.setProperty(user2, "username", "ahah");
			BeanUtils.setProperty(user2, "profile.email", "shiyangxt@126.com");// 重点
			BeanUtils.setProperty(user2, "profile.birthDate",// 重点
					new GregorianCalendar(1900, 2, 5).getTime());
			BeanUtils.setProperty(user2, "profile.address[0]", new Address("中国", "深圳", "600600", "深北大道111号"));// 重点
			System.out.println(BeanUtils.getProperty(user2, "userId"));
			System.out.println(BeanUtils.getProperty(user2, "username"));
			System.out.println(BeanUtils.getProperty(user2, "profile"));
			System.out.println(BeanUtils.getProperty(user2, "profile.email"));// 重点
			System.out.println(BeanUtils.getProperty(user2, "profile.birthDate"));// 重点
			System.out.println(BeanUtils.getProperty(user2, "profile.address[0].city"));// 重点

			System.out.println("与被复制属性值的对象的比较-------------------------------");
			System.out.println(BeanUtils.getProperty(user, "userId"));
			System.out.println(BeanUtils.getProperty(user, "username"));
			System.out.println(BeanUtils.getProperty(user, "profile"));
			System.out.println(BeanUtils.getProperty(user, "profile.email"));// 重点
			System.out.println(BeanUtils.getProperty(user, "profile.birthDate"));// 重点
			System.out.println(BeanUtils.getProperty(user, "profile.address[0].city"));// 重点
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
