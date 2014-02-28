package com.zimmer.dbbeans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_contacts_details")
public class UserContacts implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String relationshipId;
	private User owner;
	private User friend;
	private Boolean isActive;
	private Date createdAt;
	private Date updatedAt;
	private User updatedBy;
	private String ownerRoleInConnection;
	private String friendRoleInConnection;
	private String relationshipType;
	private String relationshipStatus;

	@Id
	@Column(name="relationship_id")
	public String getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY,optional= false)
	@JoinColumn(name="friend_id")
	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	@Basic(optional = false)
	@Column(name="is_relationship_active",nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Basic(optional = false)
	@Column(name="relationship_created_at",nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="relationship_updated_at",nullable = false)
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="relationship_updated_by")
	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Basic(optional=false)
	@Column(name="owner_role")
	public String getOwnerRoleInConnection() {
		return ownerRoleInConnection;
	}

	public void setOwnerRoleInConnection(String ownerRoleInConnection) {
		this.ownerRoleInConnection = ownerRoleInConnection;
	}

	@Basic(optional=false)
	@Column(name="friend_role")
	public String getFriendRoleInConnection() {
		return friendRoleInConnection;
	}

	public void setFriendRoleInConnection(String friendRoleInConnection) {
		this.friendRoleInConnection = friendRoleInConnection;
	}

	@Basic(optional = false)
	@Column(name="relationship_type")
	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	@Basic(optional = false)
	@Column(name="relationship_status")
	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

}
