package com.zimmer.dbbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zimmer.utils.UUIDGenerators;

@Entity
@Table(name = "tag_details")
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tagId;
	private String title;
	private Boolean isActive;
	private User createdBy;
	private User updatedBy;
	private Date createdAt;
	private Date updatedAt;
	private String createdFor;
	private Set<TagFeedMap> feedsForTag = new HashSet<TagFeedMap>();
	private Set<TagTaskMap> tasksForTag = new HashSet<TagTaskMap>();
	private Set<TagEventMap> eventsForTag = new HashSet<TagEventMap>();

	public Tag() {
		super();
		this.createdAt = new Date();
		this.isActive = true;
		this.tagId = UUIDGenerators.generateTagId();
	}

	public Tag(String title, User createdBy, String createdFor) {
		super();
		this.title = title;
		this.createdBy = createdBy;
		this.createdFor = createdFor;
		this.tagId = UUIDGenerators.generateTagId();
		this.isActive = true;
		this.createdAt = new Date();
	}

	public Tag(String tagId, String title, Boolean isActive, User createdBy,
			User updatedBy, Date createdAt, Date updatedAt, String createdFor) {
		super();
		this.tagId = tagId;
		this.title = title;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdFor = createdFor;
	}

	@Id
	@Column(name = "tag_id")
	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	@Basic(optional = false)
	@Column(name = "tag_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Basic(optional = false)
	@Column(name = "is_tag_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tag_created_by")
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_updated_by")
	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Basic(optional = false)
	@Column(name = "tag_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "tag_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Basic(optional = false)
	@Column(name = "tag_created_for")
	public String getCreatedFor() {
		return createdFor;
	}

	public void setCreatedFor(String createdFor) {
		this.createdFor = createdFor;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<TagFeedMap> getFeedsForTag() {
		return feedsForTag;
	}

	public void setFeedsForTag(Set<TagFeedMap> feedsForTag) {
		this.feedsForTag = feedsForTag;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<TagTaskMap> getTasksForTag() {
		return tasksForTag;
	}

	public void setTasksForTag(Set<TagTaskMap> tasksForTag) {
		this.tasksForTag = tasksForTag;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<TagEventMap> getEventsForTag() {
		return eventsForTag;
	}

	public void setEventsForTag(Set<TagEventMap> eventsForTag) {
		this.eventsForTag = eventsForTag;
	}

}
