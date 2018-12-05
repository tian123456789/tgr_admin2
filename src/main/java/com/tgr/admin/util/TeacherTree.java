package com.tgr.admin.util;

public class TeacherTree {
	String id;
	String name;
	TeacherTree[] children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TeacherTree[] getChildren() {
		return children;
	}
	public void setChildren(TeacherTree[] children) {
		this.children = children;
	}
	
	
}
