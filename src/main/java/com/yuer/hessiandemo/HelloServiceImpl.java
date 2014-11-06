package com.yuer.hessiandemo;

import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloServiceImpl implements HelloService {

	public String sayHello() {
		return "Hello World";
	}

	public Foo foo() {
		return new Foo("foo");
	}
}
