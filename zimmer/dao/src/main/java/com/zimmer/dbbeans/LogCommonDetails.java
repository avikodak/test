package com.zimmer.dbbeans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LogCommonDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean isActive;
	private Date createdAt;
	private Date updatedAt;
	private String description;
	private String logType;
	
	public LogCommonDetails() {
		super();
		this.isActive = true;
		this.createdAt = new Date();
	}

	public LogCommonDetails(Boolean isActive, Date createdAt,
			Date updatedAt, String description,String logType) {
		super();
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.description = description;
		this.logType = logType;
	}

	@Basic(optional = false)
	@Column(name="is_log_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Basic(optional = false)
	@Column(name="log_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Basic(optional = false)
	@Column(name="log_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name="log_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic(optional=false)
	@Column(name="log_type")
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}
}
