package com.dventus;
// Generated Dec 8, 2022, 4:50:42 PM by Hibernate Tools 6.1.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * UserGroup generated by hbm2java
 */
public class UserGroup implements java.io.Serializable {

	private Long id;
	private User user;
	private Role role;
	private String groupId;
	private Set userGroupUsersMaps = new HashSet(0);
	private Set userGroupRolesMaps = new HashSet(0);

	public UserGroup() {
	}

	public UserGroup(User user, Role role, String groupId, Set userGroupUsersMaps, Set userGroupRolesMaps) {
		this.user = user;
		this.role = role;
		this.groupId = groupId;
		this.userGroupUsersMaps = userGroupUsersMaps;
		this.userGroupRolesMaps = userGroupRolesMaps;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Set getUserGroupUsersMaps() {
		return this.userGroupUsersMaps;
	}

	public void setUserGroupUsersMaps(Set userGroupUsersMaps) {
		this.userGroupUsersMaps = userGroupUsersMaps;
	}

	public Set getUserGroupRolesMaps() {
		return this.userGroupRolesMaps;
	}

	public void setUserGroupRolesMaps(Set userGroupRolesMaps) {
		this.userGroupRolesMaps = userGroupRolesMaps;
	}

}
