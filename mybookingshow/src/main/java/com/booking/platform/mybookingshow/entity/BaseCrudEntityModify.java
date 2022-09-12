package com.booking.platform.mybookingshow.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseCrudEntityModify<ID> implements Serializable {
	
	private static final long serialVersionUID= 1L;
	
	@Column(name="create_date")
	private LocalDateTime createdDate;
	
	
	@Column(name="last_modified_date")
	private LocalDateTime LastModifiedDate;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="modified_by")
	private String modifiedBy;
	

	public BaseCrudEntityModify() {
		// TODO Auto-generated constructor stub
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public LocalDateTime getLastModifiedDate() {
		return LastModifiedDate;
	}


	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	

}
