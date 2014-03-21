package com.yuer.dbutils.entity;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = -5588026289920319964L;

	// Fields
	private Integer id;
	private String name;

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}