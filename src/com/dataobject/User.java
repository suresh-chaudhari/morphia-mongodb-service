package com.dataobject;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * 
 * @author suresh
 *
 */

@Entity(value="user", noClassnameStored = true)
public class User {


	@Id
	private ObjectId id;
	
	@Property("login_name")
	private String loginName;

	@Property("last_login")
	private Date lastLogin;
	
	@Property("login_duration")
	private Integer loginDuration;
	
	@Property("create_date")
	private Long createDate;
	
	@Property("last_password_change_date")
	private Long lastPasswordChangeDate;
	
	@Property("birth_date")
	private Long birthDate;

	@Property("new_password")
	private String newPassword;
	
	@Property("first_name")
	private String firstName;
	
	@Property("last_name")
	private String lastName;

	private String password;

	private String email;
	
	private String telephone;
	
	private String mobile;
	
	private Boolean status;
	
	private Address address;

	private List<String> access;
	
	private String roleName;
	
	/**
	 * Authenticaion Types 
	 */
	public enum AUTH_TYPE{
		PASSWORD_AUTH
	};

	/**
	 * Authentication type of User.
	 */
	private Integer authType;


	/**
	 * Gets the loginName.
	 * @return Returns the loginName.
	 */
	public String getLoginName() {
		return loginName;
	}


	/**
	 * Sets the loginName.
	 * @param loginName The loginName to set.
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * Gets the authType.
	 * @return Returns the authType.
	 */
	public Integer getAuthType() {
		return authType;
	}

	/**
	 * Sets the authType.
	 * @param authType The authType to set.
	 */
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	/**
	 * Gets the lastLoginTime.
	 * @return Returns the lastLogin.
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets the lastLoginTime.
	 * @param lastLogin The lastLogin to set.
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets the loginDuration.
	 * @return Returns the loginDuration.
	 */
	public Integer getLoginDuration() {
		return loginDuration;
	}

	/**
	 * Sets the loginDuration.
	 * @param loginDuration The loginDuration to set.
	 */
	public void setLoginDuration(Integer loginDuration) {
		this.loginDuration = loginDuration;
	}

	/**
	 * Gets the password.
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the newPassword
	 * NOTE: This field will only be used by the change password request. 
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 * NOTE: This field will only be used by the change password request.
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getLastPasswordChangeDate() {
		return lastPasswordChangeDate;
	}

	public void setLastPasswordChangeDate(long lastPasswordChangeDate) {
		this.lastPasswordChangeDate = lastPasswordChangeDate;
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
	
	public long getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(long birthDate) {
		this.birthDate = birthDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getAccess() {
		return access;
	}

	public void setAccess(List<String> access) {
		this.access = access;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public void setLastPasswordChangeDate(Long lastPasswordChangeDate) {
		this.lastPasswordChangeDate = lastPasswordChangeDate;
	}

	public void setBirthDate(Long birthDate) {
		this.birthDate = birthDate;
	}

	public User() {
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", lastLogin="
				+ lastLogin + ", loginDuration=" + loginDuration
				+ ", createDate=" + createDate + ", lastPasswordChangeDate="
				+ lastPasswordChangeDate + ", birthDate=" + birthDate
				+ ", newPassword=" + newPassword + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", telephone=" + telephone + ", mobile="
				+ mobile + ", status=" + status + ", address=" + address
				+ ", access=" + access + ", roleName=" + roleName
				+ ", authType=" + authType + "]";
	}

}