package com.booking.platform.mybookingshow.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "bkp_roles" ,
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "name")
	})
public class BkpRole extends BaseCrudEntityModify<Long> implements Serializable {
private static final long serialVersionUID= 1;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	

	public BkpRole() {

	}

	public BkpRole(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}