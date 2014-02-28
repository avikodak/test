package com.zimmer.dbbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="smartlist_details")
public class SmartList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String smartListId;
	private String title;
	private String description;
	private Date createdAt;
	private Date updatedAt;
	private Boolean isActive;
	private Date lastUsedOn;
	private Boolean isEdited;
	private Set<SmartListUserMap> smartListUsers;
	
	public SmartList() {
		super();
	}

	@Id
	@Column(name="smart_list_id")
	public String getSmartListId() {
		return smartListId;
	}

	public void setSmartListId(String smartListId) {
		this.smartListId = smartListId;
	}

	@Basic(optional=false)
	@Column(name="smartlist_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="smartlist_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic(optional = false)
	@Column(name="smartlist_created_at",nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="smartlist_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Basic(optional=false)
	@Column(name="is_smartlist_active",nullable=false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Basic(optional = false)
	@Column(name="smartlist_last_used_on",nullable=false)
	public Date getLastUsedOn() {
		return lastUsedOn;
	}

	public void setLastUsedOn(Date lastUsedOn) {
		this.lastUsedOn = lastUsedOn;
	}

	@Basic(optional=false)
	@Column(name="is_smartlist_edited",nullable=false)
	public Boolean getIsEdited() {
		return isEdited;
	}

	public void setIsEdited(Boolean isEdited) {
		this.isEdited = isEdited;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="smartList")
	public Set<SmartListUserMap> getSmartListUsers() {
		return smartListUsers;
	}

	public void setSmartListUsers(Set<SmartListUserMap> smartListUsers) {
		this.smartListUsers = smartListUsers;
	}

	
}
