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

import com.zimmer.utils.UUIDGenerators;

@Entity
@Table(name="like_details")
public class Like implements Serializable {

	private static final long serialVersionUID = 1L;
	private String likeId;
	private User owner;
	private Boolean isActive;
	private Date createdAt;
	private Date updatedAt;
	private Feed parentFeed;
	private Event parentEvent;
	private Task parentTask;
	private String likeFor;
	
	public Like() {
		super();
		this.createdAt = new Date();
		this.isActive = true;
		this.likeId = UUIDGenerators.generateLikeId();
	}

	public Like(User owner, Date createdAt, String likeFor) {
		super();
		this.owner = owner;
		this.createdAt = createdAt;
		this.likeFor = likeFor;
		this.likeId = UUIDGenerators.generateLikeId();
		this.isActive = true;
		this.createdAt = new Date();
	}

	public Like(String likeId, User owner, Boolean isActive, Date createdAt,
			Date updatedAt, Feed parentFeed) {
		super();
		this.likeId = likeId;
		this.owner = owner;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.parentFeed = parentFeed;
	}

	@Id
	@Column(name="like_id")
	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="like_owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Column(name="is_like_active",nullable=false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@Column(name="like_created_at",nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name="like_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="like_feed_id")
	public Feed getParentFeed() {
		return parentFeed;
	}

	public void setParentFeed(Feed parentFeed) {
		this.parentFeed = parentFeed;
	}	
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="like_event_id")
	public Event getParentEvent() {
		return parentEvent;
	}

	public void setParentEvent(Event parentEvent) {
		this.parentEvent = parentEvent;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="like_task_id")
	public Task getParentTask() {
		return parentTask;
	}

	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}

	@Basic(optional=false)
	@Column(name="like_for")
	public String getLikeFor() {
		return likeFor;
	}

	public void setLikeFor(String likeFor) {
		this.likeFor = likeFor;
	}
}
