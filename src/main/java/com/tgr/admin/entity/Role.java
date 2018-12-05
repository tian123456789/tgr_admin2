package com.tgr.admin.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority , java.io.Serializable{
    
	private static final long serialVersionUID = -7506973583694786601L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name")
    private String name;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    @JsonIgnore
    @ManyToMany
	@JoinTable(name = "role_menu", //
			joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }, //
			inverseJoinColumns = { @JoinColumn(name = "menu_id", referencedColumnName = "id") })
	private List<Menu> menus = new ArrayList<Menu>();
    
    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
	public String getAuthority() {
		return name;
	}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
