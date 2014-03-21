package com.ka.configuration.Test;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.junit.Test;

public class ConfigurationLoadTest {

	@Test
	public void testPropertiesConfig() {
		try {
			PropertiesConfiguration propertiesConfig = new PropertiesConfiguration("log4j.properties");
			propertiesConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
			while(true){
				String log = propertiesConfig.getString("log4j.appender.stdout");
				System.out.println(log);
				Thread.sleep(1000);
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testXmlConfig() {
		try {
			XMLConfiguration config = new XMLConfiguration("student.xml");
			// 对于单独元素的话，可以直接通过标签名获取值
			String str = config.getString("boy");
			System.out.println(str);
			// 对于循环出现的嵌套元素，可以通过父元素.子元素来获取集合值
			List<String> names = config.getList("student.name");
			System.out.println(Arrays.toString(names.toArray()));
			// 对于一个单独元素包含的值有多个的话如：a,b,c,d 可以通过获取集合
			List<String> titles = config.getList("title");
			System.out.println(Arrays.toString(titles.toArray()));
			// 对于标签元素的属性，可以通过 标签名[@属性名] 这样的方式获取
			String size = config.getString("ball[@size]");
			System.out.println(size);
			// 对于嵌套的标签的话，想获得某一项的话可以通过 标签名(索引名) 这样方式获取
			String id = config.getString("student(1)[@id]");
			System.out.println(id);

			String go = config.getString("student.name(0)[@go]");
			System.out.println(go);
			/**
			 * 依次输出结果为 tom [lily, lucy] [abc, cbc, bbc, bbs] 20 2 common1
			 * 
			 */
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
