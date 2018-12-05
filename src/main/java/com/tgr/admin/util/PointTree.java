package com.tgr.admin.util;

public class PointTree {
	String id;
	String label;
	int type;
	PointTree[] children;
	boolean islife;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public PointTree[] getChildren() {
		return children;
	}
	public void setChildren(PointTree[] children) {
		this.children = children;
	}
	public boolean isIslife() {
		return islife;
	}
	public void setIslife(boolean islife) {
		this.islife = islife;
	}
	
	
}
