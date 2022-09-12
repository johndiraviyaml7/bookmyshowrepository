package com.booking.platform.mybookingshow.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "bkp_users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email"),
					@UniqueConstraint(columnNames = "mobile_number") 
		})
public class BkpUser extends BaseCrudEntityModify<Long> implements Serializable {
	private static final long serialVersionUID= 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 20)
	private String username;

	@NotNull
	@Size(max = 50)
	@Email
	private String email;

	@NotNull
	@Size(max = 120)
	private String password;
	
	@NotNull
	@Size(max = 120)
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@NotNull
	@Size(max = 120)
	@Column(name="full_name")
	private String fullName;
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "bkp_user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<BkpRole> bkpRoles = new HashSet<>();

	public BkpUser() {
	}

	public BkpUser(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public Set<BkpRole> getBk_Roles() {
		return bkpRoles;
	}

	public void setBk_Roles(Set<BkpRole> bkpRoles) {
		this.bkpRoles = bkpRoles;
	}

	public Set<BkpRole> getRoles() {
		return bkpRoles;
	}

	public void setRoles(Set<BkpRole> bkpRoles) {
		this.bkpRoles = bkpRoles;
	}
}
