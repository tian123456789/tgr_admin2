package com.tgr.admin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tgr.admin.util.serializer.ObjectNullSerializer;

/**
 * 
 * 菜单表
 *
 */
@Entity
@Table(name = "menu")
public class Menu extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8848939155690492361L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "icon")
	private String icon;

	@Column(name = "level")
	private int level;

	@Column(name = "parent_id")
	private int parentId;
/*
	@OneToMany
	@JoinColumn(name = "parent_id")
	private List<Menu> children;*/

	@Column(name = "tips")
	private String tips;

	@Column(name = "url")
	private String url;

	@Column(name = "sort")
	private String sort;

	@JsonSerialize(nullsUsing = ObjectNullSerializer.class)
	@JsonIgnore
	@ManyToMany(mappedBy = "menus")
	private List<Role> roles = new ArrayList<Role>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
