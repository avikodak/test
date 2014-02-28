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
@Table(name="file_upload_details")
public class FileUpload implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fileUploadId;
	private String fileUrl;
	private Double fileSize;
	private String fileType;
	private Date createdAt;
	private Date updatedAt;
	private User updatedBy;
	private Boolean isActive;
	private Feed parentFeed;
	private Event parentEvent;
	private Task parentTask;
	private User fileOwner;

	public FileUpload() {
		super();
		this.fileUploadId = UUIDGenerators.generateFileUploadId();
		this.createdAt = new Date();
		this.isActive = true;
	}

	public FileUpload(String fileUrl, Double fileSize, String fileType,
			Boolean isActive, User fileOwner) {
		super();
		this.fileUrl = fileUrl;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.isActive = isActive;
		this.fileOwner = fileOwner;
		this.fileUploadId = UUIDGenerators.generateFileUploadId();
		this.createdAt = new Date();
		this.isActive = true;
	}

	public FileUpload(String fileUploadId, String fileUrl, Double fileSize,
			String fileType, Date createdAt, Date updatedAt, User updatedBy,
			Boolean isActive, Feed parentFeed, User fileOwner) {
		super();
		this.fileUploadId = fileUploadId;
		this.fileUrl = fileUrl;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
		this.isActive = isActive;
		this.parentFeed = parentFeed;
		this.fileOwner = fileOwner;
	}

	@Id
	@Column(name="file_upload_id")
	public String getFileUploadId() {
		return fileUploadId;
	}

	public void setFileUploadId(String fileUploadId) {
		this.fileUploadId = fileUploadId;
	}

	@Basic(optional=false)
	@Column(name="uploaded_file_url",nullable=false)
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Basic(optional=false)
	@Column(name="file_size",nullable=false)
	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	@Basic(optional=false)
	@Column(name="file_type",nullable=false)
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Basic(optional=false)
	@Column(name="file_created_at",nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="file_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name="updated_by_user_id")
	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Basic(optional=false)
	@Column(name="is_file_active",nullable=false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="file_feed_id")
	public Feed getParentFeed() {
		return parentFeed;
	}

	public void setParentFeed(Feed parentFeed) {
		this.parentFeed = parentFeed;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="file_event_id")
	public Event getParentEvent() {
		return parentEvent;
	}

	public void setParentEvent(Event parentEvent) {
		this.parentEvent = parentEvent;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="file_task_id")
	public Task getParentTask() {
		return parentTask;
	}

	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name="file_owner_id")
	public User getFileOwner() {
		return fileOwner;
	}

	public void setFileOwner(User fileOwner) {
		this.fileOwner = fileOwner;
	}


}

