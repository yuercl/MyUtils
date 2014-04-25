package com.yuer.zookeeperTest;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.zkclient.IZkClient;
import com.github.zkclient.IZkDataListener;
import com.github.zkclient.ZkClient;

public class TestMaster {

	static boolean flag = false;

	static IZkClient zkClient = new ZkClient("192.168.16.48:2181");

	public static void main(String[] args) throws Exception {
		String parentNode = "parent";
		String subNode = "sub";
		ExecutorService executorService = Executors.newCachedThreadPool();
		zkClient.createEphemeralSequential(String.format("/%s/%s", parentNode, subNode), new String("sub1").getBytes("utf-8"));
		final String subPath = zkClient.createEphemeralSequential(String.format("/%s/%s", parentNode, subNode), new String("sub1").getBytes("utf-8"));
		System.err.println("我的 subpath : " + subPath);
		List<String> childList = zkClient.getChildren(String.format("/%s", parentNode));
		System.err.println("全部的 child 节点 : " + childList.toString());

		// 列表中只有一个子节点, 那肯定就是subPath,他就是Master
		if (childList.size() == 1) {
			flag = true;
		} else {
			Collections.sort(childList);
			// 找到在 childList 里面的值,原来的值是 /parent/sub0000000014 ,
			// 现在的值应该是sub0000000014
			String subchildPath = subPath.substring(String.format("/%s/", parentNode).length());
			int index = childList.indexOf(subchildPath);
			String waitPath = null;
			if (index > -1) {
				waitPath = String.format("/%s/%s", parentNode, childList.get(index - 1));
				System.err.println("小于自己的最小节点 ： " + waitPath);
				watcherPath(zkClient, waitPath);
			}

		}
		executorService.submit(new Runnable() {

			public void run() {
				while (true) {
					if (flag) {
						try {
							todo(subPath);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						System.err.println(flag);
						try {
							Thread.currentThread().sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
	}

	private static void todo(String subPath) throws Exception {
		System.err.println("生成图片ing....");
		Thread.currentThread().sleep(5000);
		System.err.println("生成图片完毕");
		zkClient.delete(subPath);
		flag = false;
	}

	private static void watcherPath(IZkClient zkClient, String waitPath) {
		zkClient.subscribeDataChanges(waitPath, new IZkDataListener() {

			public void handleDataChange(String dataPath, byte[] data) throws Exception {

			}

			public void handleDataDeleted(String dataPath) throws Exception {
				System.err.println("开始删除了 : " + dataPath);
				flag = true;
			}
		});
	}

}
