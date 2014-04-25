package com.yuer.zookeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

public class ZkClient {

	private static ZooKeeper zk = null;

	private static Properties prop = new Properties();
	private static final String env = "env.properties";
	private static final String charset = "UTF-8";
	static {
		try {
			prop.load(ZkClient.class.getClassLoader().getResourceAsStream(env));
		} catch (Exception e) {
			throw new RuntimeException("load zk config file[" + env
					+ "] error.");
		}
		String hosts = prop.getProperty("zk.server.host");
		Integer times = Integer.valueOf(prop.getProperty("zk.wait.time"));
		try {
			zk = new ZooKeeper(hosts, times, null);
		} catch (IOException e) {
			throw new RuntimeException("cat't connection zk with[" + prop + "]");
		}
	}

	public static ZooKeeper getConnection() {
		return zk;
	}

	public static String addPerNode(String path, String data) throws Exception {
		return zk.create(path, toByte(data), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
	}

	public static String addPerSeqNode(String path, String data)
			throws Exception {
		return create(path, data, Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT_SEQUENTIAL);
	}

	public static String addEphNode(String path, String data) throws Exception {
		return create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
	}

	public static String addEphSeqNode(String path, String data)
			throws Exception {
		return create(path, data, Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL);
	}

	public static void main(String[] args) throws Exception {
		// 删除历史创建的znode
		clearZnode();
		// 1.创建persist znode
		addPerNode("/persist", "persist");

		// 2.创建persist seq znode
		addPerSeqNode("/persist/persist_seq", "persist_seq");

		// 3.创建 ephemeral znode,session过期后该节点将不存在
		addEphNode("/ephemeral", "ephemeral");

		// 4.创建ephemeral seq znode,session过期后该节点将不存在
		addEphSeqNode("/ephemeral_seq", "ephemeral_seq");
	}

	private static byte[] toByte(String data)
			throws UnsupportedEncodingException {
		if (data == null) {
			return new byte[0];
		}
		return data.getBytes(charset);
	}

	private static String create(String path, String data, List<ACL> acl,
			CreateMode createMode) throws Exception {
		return zk.create(path, toByte(data), acl, createMode);
	}

	private static void clearZnode() throws Exception {
		List<String> children = zk.getChildren("/", null);
		for (String path : children) {
			if ("zookeeper".equalsIgnoreCase(path)) {
				continue;
			}
			// 版本号指定为-1为删除所有版本
			zk.delete("/" + path, -1);
			System.out.println(path + " had been deleted.");
		}
	}
}
