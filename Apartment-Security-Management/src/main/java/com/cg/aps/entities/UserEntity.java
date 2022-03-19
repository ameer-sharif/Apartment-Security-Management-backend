package com.cg.aps.entities;

//import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "userentity")
public class UserEntity {

//	private static final long serialVersionUID = 5926468583005150707L;
	@Id

	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(length = 30)
	
	private String firstName;
	
	@Column(length = 30)
	private String lastName;
	
	@Column(length = 30)
	private String loginId;
	
	@Column(length = 30)
	private String password;
	
	@Column(length = 30)
	private String mobileNo;
	
	@Column(length = 30)
	private String emailId;
	
	@Column(length = 30)
	private long roleId;
	
	public UserEntity(){
		
	}
	
	public UserEntity(int userId, String password){
		this.userId = userId;
		this.password = password;
	}
	
	public UserEntity(int userId, String firstName, String lastName, String loginId, String password, String mobileNo,
			String emailId, long roleId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginId = loginId;
		this.password = password;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.roleId = roleId;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", loginId="
				+ loginId + ", password=" + password + ", mobileNo=" + mobileNo + ", emailId=" + emailId + ", roleId="
				+ roleId + "]";
	}

	
	

	
	
}