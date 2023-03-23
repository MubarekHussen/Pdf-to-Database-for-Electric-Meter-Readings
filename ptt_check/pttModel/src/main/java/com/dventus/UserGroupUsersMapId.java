package com.dventus;
// Generated Dec 8, 2022, 4:50:42 PM by Hibernate Tools 6.1.3.Final

/**
 * UserGroupUsersMapId generated by hbm2java
 */
public class UserGroupUsersMapId implements java.io.Serializable {

	private long GId;
	private long UId;

	public UserGroupUsersMapId() {
	}

	public UserGroupUsersMapId(long GId, long UId) {
		this.GId = GId;
		this.UId = UId;
	}

	public long getGId() {
		return this.GId;
	}

	public void setGId(long GId) {
		this.GId = GId;
	}

	public long getUId() {
		return this.UId;
	}

	public void setUId(long UId) {
		this.UId = UId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserGroupUsersMapId))
			return false;
		UserGroupUsersMapId castOther = (UserGroupUsersMapId) other;

		return (this.getGId() == castOther.getGId()) && (this.getUId() == castOther.getUId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getGId();
		result = 37 * result + (int) this.getUId();
		return result;
	}

}
