package com.yuer.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 第一步：生成CacheManager对象 第二步：生成Cache对象 第三步：向Cache对象里添加由key,value组成的键值对的Element元素
 * 
 * @author mahaibo
 * 
 */
public class EhcacheTest {

	public static void main(String[] args) {
		// 指定ehcache.xml的位置
		String fileName = "E:\\1008\\workspace\\ehcachetest\\ehcache.xml";
		CacheManager manager = new CacheManager(fileName);
		// 取出所有的cacheName
		String names[] = manager.getCacheNames();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
		// 根据cacheName生成一个Cache对象
		// 第一种方式：
		Cache cache = manager.getCache(names[0]);

		// 第二种方式，ehcache里必须有defaultCache存在,"test"可以换成任何值
		// Cache cache = new Cache("test", 1, true, false, 5, 2);
		// manager.addCache(cache);

		// 向Cache对象里添加Element元素，Element元素有key，value键值对组成
		cache.put(new Element("key1", "values1"));
		Element element = cache.get("key1");

		System.out.println(element.getValue());
		Object obj = element.getObjectValue();
		System.out.println((String) obj);
		manager.shutdown();

	}

}
