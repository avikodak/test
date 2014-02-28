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
@Table(name="task_details")
public class Task implements Serializable{

	private static final long serialVersionUID = 1L;

	private String taskId;
	private User owner;
	private String title;
	private String description;
	private String type;
	private String status;
	private String comments;
	private Double percentage;
	private Boolean isActive;
	private Boolean isValid;
	private Date createdAt;
	private Date updatedAt;
	private Date deadline;
	private Set<Comment> taskComments = new HashSet<Comment>();
	private Set<Like> taskLikes = new HashSet<Like>();
	private Set<FileUpload> taskFileUploads = new HashSet<FileUpload>();
	private Set<TaskLog> taskLogs = new HashSet<TaskLog>();
	private Set<TagTaskMap> taskTags = new HashSet<TagTaskMap>();
	private Set<TaskUserMap> taskMappedUsers = new HashSet<TaskUserMap>();
	
	public Task() {
		super();
		this.createdAt = new Date();
		this.isActive = true;
		this.isValid = true;
		this.status = "ASSIGNED";
		this.taskId = UUIDGenerators.generateTaskId();
	}
	
	public Task(User owner, String title, String description, String type,Date deadline) {
		super();
		this.owner = owner;
		this.title = title;
		this.description = description;
		this.type = type;
		this.taskId = UUIDGenerators.generateTaskId();
		this.createdAt = new Date();
		this.deadline = deadline;
		this.isActive = true;
		this.isValid = true;
		this.status = "ASSIGNED";
	}

	public Task(User owner, String title, String description, String type,
			String status, String comments, Double percentage,
			Boolean isActive, Boolean isValid, Date createdAt, Date updatedAt,
			Date deadline) {
		super();
		this.owner = owner;
		this.title = title;
		this.description = description;
		this.type = type;
		this.status = status;
		this.comments = comments;
		this.percentage = percentage;
		this.isActive = isActive;
		this.isValid = isValid;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deadline = deadline;
	}

	@Id
	@Column(name="task_id")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="task_owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Basic(optional=false)
	@Column(name="task_title",nullable=false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="task_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic(optional=false)
	@Column(name="task_type",nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Basic(optional=false)
	@Column(name="task_status",nullable=false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="tasks_other_comments")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Basic(optional=false)
	@Column(name="task_completed_percentage",nullable=false)
	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	@Basic(optional=false)
	@Column(name="is_task_active",nullable=false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Basic(optional=false)
	@Column(name="is_task_valid",nullable=false)
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	@Column(name="task_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="task_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Basic(optional=false)
	@Column(name="task_deadline",nullable=false)
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parentTask")
	public Set<Comment> getTaskComments() {
		return taskComments;
	}

	public void setTaskComments(Set<Comment> taskComments) {
		this.taskComments = taskComments;
	}

	public void addCommentToTask(Comment comment){
		this.taskComments.add(comment);
		comment.setParentTask(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parentTask")
	public Set<Like> getTaskLikes() {
		return taskLikes;
	}

	public void setTaskLikes(Set<Like> taskLikes) {
		this.taskLikes = taskLikes;
	}

	public void addLikesToTask(Like like){
		this.taskLikes.add(like);
		like.setParentTask(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parentTask")
	public Set<FileUpload> getTaskFileUploads() {
		return taskFileUploads;
	}

	public void setTaskFileUploads(Set<FileUpload> taskFileUploads) {
		this.taskFileUploads = taskFileUploads;
	}

	public void addFileUploadToTask(FileUpload fileUpload){
		this.taskFileUploads.add(fileUpload);
		fileUpload.setParentTask(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parentTask")
	public Set<TaskLog> getTaskLogs() {
		return taskLogs;
	}

	public void setTaskLogs(Set<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}

	public void addLogToTask(TaskLog taskLog){
		this.taskLogs.add(taskLog);
		taskLog.setParentTask(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="task")
	public Set<TagTaskMap> getTaskTags() {
		return taskTags;
	}

	public void setTaskTags(Set<TagTaskMap> taskTags) {
		this.taskTags = taskTags;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="mappedTask")
	public Set<TaskUserMap> getTaskMappedUsers() {
		return taskMappedUsers;
	}

	public void setTaskMappedUsers(Set<TaskUserMap> taskMappedUsers) {
		this.taskMappedUsers = taskMappedUsers;
	}

}
