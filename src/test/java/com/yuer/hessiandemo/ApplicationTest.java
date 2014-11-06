package com.yuer.hessiandemo;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class, TestConfig.class })
public class ApplicationTest {

	@Autowired
	private HelloService helloClient;

	@Test
	public void shouldSayHello() {

		// when
		String message = helloClient.sayHello();

		System.out.println(message);

		then(message).isNotEmpty().isEqualTo("Hello World");
	}

	@Test
	public void shouldReceiveFoo() {

		// when
		Foo foo = helloClient.foo();
		
		System.out.println(foo.toString());

		then(foo.getName()).isEqualTo("foo");
	}

}
