package com.cg.aps.entities;


	import javax.persistence.Column;
import javax.persistence.Entity;
//	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
//	import javax.persistence.Inheritance;
//	import javax.persistence.InheritanceType;
	import javax.persistence.Table;
//	import javax.validation.constraints.NotEmpty;
	


	@Entity
	@Table(name = "adminentity")
//	@Inheritance(strategy = InheritanceType.JOINED)
	public class AdminEntity {
		
		@Id
//		@NotEmpty
		@Column(length = 30)
		private int adminId;
		@Column(length = 30)
		private String firstName;
		@Column(length = 30)
		private String lastName;
		@Column(length = 50)
		private String emailId;
		@Column(length = 30)
		private String phoneNo;
		@Column(length = 30)
		private String password;
		
		public AdminEntity() {
			
		}
		
		
		public AdminEntity(int adminId, String firstName, String lastName, String emailId, String phoneNo, String password) {
			super();
			this.adminId = adminId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.emailId = emailId;
			this.phoneNo = phoneNo;
			this.password = password;
		}
		


		public int getAdminId() {
			return adminId;
		}

		public void setAdminId(int adminId) {
			this.adminId = adminId;
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

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	
		@Override
		public String toString() {
			return "Admin [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
					+ emailId + ", phoneNo=" + phoneNo + ", password=" + password + "]";
		}

		
	
		
		
	}