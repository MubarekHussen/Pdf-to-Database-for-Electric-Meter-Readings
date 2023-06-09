package com.dventus;
// Generated Dec 8, 2022, 4:50:42 PM by Hibernate Tools 6.1.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role implements java.io.Serializable {

	private Long id;
	private String roleId;
	private Set userGroups = new HashSet(0);
	private Set userGroupRolesMaps = new HashSet(0);

	public Role() {
	}

	public Role(String roleId, Set userGroups, Set userGroupRolesMaps) {
		this.roleId = roleId;
		this.userGroups = userGroups;
		this.userGroupRolesMaps = userGroupRolesMaps;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Set getUserGroups() {
		return this.userGroups;
	}

	public void setUserGroups(Set userGroups) {
		this.userGroups = userGroups;
	}

	public Set getUserGroupRolesMaps() {
		return this.userGroupRolesMaps;
	}

	public void setUserGroupRolesMaps(Set userGroupRolesMaps) {
		this.userGroupRolesMaps = userGroupRolesMaps;
	}

}
