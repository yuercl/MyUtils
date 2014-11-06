package com.yuer.hessiandemo;

import java.net.MalformedURLException;

import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;

public class SpringTest2 {
	@Test
	public void shouldSayHello() throws MalformedURLException {

		String url = "http://localhost:8080/HelloService";

		HessianProxyFactory factory = new HessianProxyFactory();
		HelloService basic = (HelloService) factory.create(HelloService.class, url);

		System.out.println("hello " + basic.sayHello());
	}

}