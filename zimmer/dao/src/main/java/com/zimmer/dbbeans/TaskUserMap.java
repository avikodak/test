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
@Table(name="task_user_map")
public class TaskUserMap implements Serializable{

	private static final long serialVersionUID = 1L;

	@Embeddable
	@SuppressWarnings("unused")
	private static class Key implements Serializable {
		private static final long serialVersionUID = 1L;
		private String taskId;
		private String userId;

		public Key() {
			super();
		}

		@Basic(optional = false)
		@Column(name="mapped_task_id")
		public String getTaskId() {
			return taskId;
		}

		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}

		@Basic(optional = false)
		@Column(name="mapped_user_id")
		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
			+ ((taskId == null) ? 0 : taskId.hashCode());
			result = prime * result
			+ ((userId == null) ? 0 : userId.hashCode());
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
			Key other = (Key) obj;
			if (taskId == null) {
				if (other.taskId != null)
					return false;
			} else if (!taskId.equals(other.taskId))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}
	}

	private Integer sno;
	private Key linkId;
	private Date createdAt;
	private User owner;
	private Task mappedTask;
	private User mappedUser;

	
	public TaskUserMap() {
		super();
		this.createdAt = new Date();
	}

	public TaskUserMap(Date createdAt, User owner, Task mappedTask,
			User mappedUser) {
		super();
		this.createdAt = createdAt;
		this.owner = owner;
		this.mappedTask = mappedTask;
		this.mappedUser = mappedUser;
		this.linkId.taskId = mappedTask.getTaskId();
		this.linkId.userId = mappedUser.getUserId();
		mappedTask.getTaskMappedUsers().add(this);
		mappedUser.getUserTaskMap().add(this);
	}

	public TaskUserMap(User owner, Task mappedTask, User mappedUser) {
		super();
		this.owner = owner;
		this.mappedTask = mappedTask;
		this.mappedUser = mappedUser;
		this.linkId.taskId = mappedTask.getTaskId();
		this.linkId.userId = mappedUser.getUserId();
		mappedTask.getTaskMappedUsers().add(this);
		mappedUser.getUserTaskMap().add(this);
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sno")
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	@Embedded
	public Key getLinkId() {
		return linkId;
	}

	public void setLinkId(Key linkId) {
		this.linkId = linkId;
	}

	@Basic(optional=false)
	@Column(name="task_user_map_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="owner_id",nullable = false)
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="mapped_task_id",nullable=false,insertable=false,updatable=false)
	public Task getMappedTask() {
		return mappedTask;
	}

	public void setMappedTask(Task task) {
		this.mappedTask = task;
	}

	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="mapped_user_id",nullable=false,insertable=false,updatable=false)
	public User getMappedUser() {
		return mappedUser;
	}

	public void setMappedUser(User mappedUser) {
		this.mappedUser = mappedUser;
	}
	
}
