package com.zimmer.dbbeans;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UserDetailsCommon implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private String type;
	private Boolean isActive;

	public UserDetailsCommon() {
		this.isActive = true;
	}

	public UserDetailsCommon(User user, String type, Boolean isActive) {
		super();
		this.user = user;
		this.type = type;
		this.isActive = isActive;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="owner_id",nullable=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Basic(optional=false)
	@Column(name="user_type",nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Basic(optional=false)
	@Column(name="is_active",nullable=false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
