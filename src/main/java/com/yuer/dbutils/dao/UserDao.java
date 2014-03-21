package com.yuer.dbutils.dao;

import java.util.List;

import com.yuer.dbutils.entity.User;

public class UserDao extends BaseDao<User> {

	public List<User> getAll() {
		return super.find("select * from _user");
	}

	public void saveGeo(User p) {
		super.save("update _user set name = ? where id = ?", p.getId(), p.getName());
	}
}
