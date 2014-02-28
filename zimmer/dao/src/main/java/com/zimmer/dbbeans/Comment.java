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
@Table(name="comment_details")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	private String commentId;
	private User owner;
	private String content;
	private Boolean containsAttachments;
	private Boolean isActive;
	private Boolean isEdited;
	private Date createdAt;
	private Date updatedAt;
	private String commentFor;
	private Feed parentFeed;
	private Event parentEvent;
	private Task parentTask;
	/*private Set<FileUpload> commentFiles;*/
	
	public Comment() {
		super();
		this.commentId = UUIDGenerators.generateCommentId();
		this.createdAt = new Date();
		this.isActive = true;
		this.isEdited = true;
	}

	public Comment(String commentId, User owner, String content,
			Boolean containsAttachments, Boolean isActive, Boolean isEdited,
			Date createdAt, Date updatedAt, Feed parentFeed) {
		super();
		this.commentId = commentId;
		this.owner = owner;
		this.content = content;
		this.containsAttachments = containsAttachments;
		this.isActive = isActive;
		this.isEdited = isEdited;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.parentFeed = parentFeed;
	}



	@Id
	@Column(name="comment_id")
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name="comment_owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Basic(optional=false)
	@Column(name="comment_content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Basic(optional=false)
	@Column(name="comment_contains_attachments")
	public Boolean getContainsAttachments() {
		return containsAttachments;
	}

	public void setContainsAttachments(Boolean containsAttachments) {
		this.containsAttachments = containsAttachments;
	}

	@Basic(optional=false)
	@Column(name="is_comment_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Basic(optional=false)
	@Column(name="is_comment_edited")
	public Boolean getIsEdited() {
		return isEdited;
	}

	public void setIsEdited(Boolean isEdited) {
		this.isEdited = isEdited;
	}

	@Basic(optional=false)
	@Column(name="comment_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="comment_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="parent_feed_id")
	public Feed getParentFeed() {
		return parentFeed;
	}

	public void setParentFeed(Feed parentFeed) {
		this.parentFeed = parentFeed;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="parent_event_id")
	public Event getParentEvent() {
		return parentEvent;
	}

	public void setParentEvent(Event parentEvent) {
		this.parentEvent = parentEvent;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="parent_task_id")
	public Task getParentTask() {
		return parentTask;
	}

	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}

	@Basic(optional=false)
	@Column(name="comment_for")
	public String getCommentFor() {
		return commentFor;
	}

	public void setCommentFor(String commentFor) {
		this.commentFor = commentFor;
	}
	
	/*public Set<FileUpload> getCommentFiles() {
		return commentFiles;
	}

	public void setCommentFiles(Set<FileUpload> commentFiles) {
		this.commentFiles = commentFiles;
	}*/	
}
