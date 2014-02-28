package com.zimmer.dbbeans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tag_task_map")
public class TagTaskMap implements Serializable{

	private static final long serialVersionUID = 1L;
	@Embeddable
	@SuppressWarnings("unused")
	private static class key implements Serializable {
		private static final long serialVersionUID = 2L;
		private String tagId;
		private String taskId;

		public key() {
			super();
		}

		@Basic(optional = false)
		@Column(name = "tag_id")
		public String getTagId() {
			return tagId;
		}

		public void setTagId(String tagId) {
			this.tagId = tagId;
		}

		@Basic(optional = false)
		@Column(name = "task_id")
		public String getTaskId() {
			return taskId;
		}

		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
			result = prime * result
			+ ((taskId == null) ? 0 : taskId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			key other = (key) obj;
			if (tagId == null) {
				if (other.tagId != null)
					return false;
			} else if (!tagId.equals(other.tagId))
				return false;
			if (taskId == null) {
				if (other.taskId != null)
					return false;
			} else if (!taskId.equals(other.taskId))
				return false;
			return true;
		}

	}

	private Integer sno;
	private key linkId = new key();
	private Date createdAt;
	private Date updatedAt;
	private User createdBy;
	private Boolean isActive;
	private Tag tag;
	private Task task;

	public TagTaskMap() {
		super();
		this.createdAt = new Date();
	}

	public TagTaskMap(Date createdAt, Date updatedAt, User createdBy,
			Boolean isActive, Tag tag, Task task) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.isActive = isActive;
		this.tag = tag;
		this.task = task;
		this.linkId.tagId = tag.getTagId();
		this.linkId.taskId = task.getTaskId();
		task.getTaskTags().add(this);
		tag.getTasksForTag().add(this);
	}

	public TagTaskMap(User createdBy, Tag tag, Task task) {
		super();
		this.createdBy = createdBy;
		this.createdAt = new Date();
		this.tag = tag;
		this.task = task;
		this.linkId.tagId = tag.getTagId();
		this.linkId.taskId = task.getTaskId();
		task.getTaskTags().add(this);
		tag.getTasksForTag().add(this);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sno")
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	@Embedded
	public key getLinkId() {
		return linkId;
	}

	public void setLinkId(key linkId) {
		this.linkId = linkId;
	}

	@Basic(optional = false)
	@Column(name = "tag_task_map_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "tag_task_map_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, optional = false)
	@JoinColumn(name="tag_task_map_owner_id")
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Basic(optional = false)
	@Column(name = "is_tag_task_map_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, optional = false)
	@JoinColumn(name="tag_id",updatable=false,insertable=false)
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, optional = false)
	@JoinColumn(name="task_id",updatable=false,insertable=false)
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
