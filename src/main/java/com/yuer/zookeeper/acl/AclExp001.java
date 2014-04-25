package com.yuer.zookeeper.acl;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import com.yuer.zookeeper.ZkClient;

public class AclExp001 {
	public static void main(String[] args) throws Exception {
		ZooKeeper zk = ZkClient.getConnection();
		// ZkClient.addPerNode("/acl", "acl");
		// zk.addAuthInfo("auth", "".getBytes());
		// zk.addAuthInfo("world", "anyone".getBytes());
		// zk.addAuthInfo("ip", "10.12.16.10".getBytes());
		zk.addAuthInfo("digest", "admin:admin".getBytes());
		zk.addAuthInfo("digest", "zhangfucai:zhangfucai".getBytes());
		List<ACL> acl = new ArrayList<ACL>();
		ACL auth = new ACL(Perms.READ, Ids.ANYONE_ID_UNSAFE);
		acl.add(auth);
		ACL a = new ACL(Perms.ADMIN, Ids.AUTH_IDS);
		acl.add(a);
		// zk.setACL("/acl", acl, -1);
		zk.create("/acl01", "123".getBytes(), acl, CreateMode.PERSISTENT);
		// zk.
		// zk.create("/acl/auth", "auth".getBytes(), acl,
		// CreateMode.PERSISTENT);
		// List<ACL> result = zk.getACL("/acl/auth", null);
		// for (ACL obj : result) {
		// System.out.println(obj);
		// }
	}

}
