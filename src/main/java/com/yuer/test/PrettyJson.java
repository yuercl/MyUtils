package com.yuer.test;

import java.io.FileReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class PrettyJson {
	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		MyClass myObject = mapper.readValue(new FileReader("src/main/resources/input.json"), MyClass.class);
		// this is Jackson 1.x API only:
		ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
		// ***IMPORTANT!!!*** for Jackson 2.x use the line below instead of the
		// one above:
		// ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
		System.out.println(writer.writeValueAsString(myObject));
	}
}

class MyClass {
	String one;
	String[] two;
	MyOtherClass three;

	public String getOne() {
		return one;
	}

	void setOne(String one) {
		this.one = one;
	}

	public String[] getTwo() {
		return two;
	}

	void setTwo(String[] two) {
		this.two = two;
	}

	public MyOtherClass getThree() {
		return three;
	}

	void setThree(MyOtherClass three) {
		this.three = three;
	}
}

class MyOtherClass {
	String four;
	String[] five;

	public String getFour() {
		return four;
	}

	void setFour(String four) {
		this.four = four;
	}

	public String[] getFive() {
		return five;
	}

	void setFive(String[] five) {
		this.five = five;
	}
}