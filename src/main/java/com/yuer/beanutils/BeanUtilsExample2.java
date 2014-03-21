package com.yuer.beanutils;

import java.util.GregorianCalendar;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsExample2 {
	// 动态创建属性
	public static void main(String args[]) throws Exception {

		LazyDynaBean hh = new LazyDynaBean();
		hh.set("country", "中国");
		hh.set("city", "北京");
		hh.set("postCode", "100120");
		hh.set("addr", "aaaaaaa");

		LazyDynaBean bb = new LazyDynaBean();
		bb.set("phone", "home", "11011011");
		bb.set("phone", "office", "111111");
		bb.set("email", "sh@126.com");
		bb.set("address", 0, hh);
		bb.set("birthDate", new GregorianCalendar(1990, 3, 29).getTime());

		LazyDynaBean tt = new LazyDynaBean();
		tt.set("userId", new Long(8888888));
		tt.set("gggg", "施杨");
		tt.set("password", "sgsgsgsg");
		tt.set("dddd", bb);

		System.out.println(BeanUtils.getProperty(tt, "gggg"));
		System.out.println(BeanUtils.getProperty(tt, "dddd.birthDate"));
		System.out.println(BeanUtils.getProperty(tt, "dddd.address[0].addr"));
		System.out.println(BeanUtils.getProperty(tt, "dddd.phone(office)"));
	}
}