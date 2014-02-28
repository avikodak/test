package com.zimmer.dbbeans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name="task_log")
public class TaskLog implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer logId;
	private LogCommonDetails logCommonDetails;
	private LogCommonIds logCommonIds;
	private Task parentTask;
	private User logOwer;
	private Group logOwningGroup;

	public TaskLog() {
		super();
	}

	public TaskLog(LogCommonDetails logCommonDetails,
			LogCommonIds logCommonIds, Task parentTask, User logOwer,
			Group logOwningGroup) {
		super();
		this.logCommonDetails = logCommonDetails;
		this.logCommonIds = logCommonIds;
		this.parentTask = parentTask;
		this.logOwer = logOwer;
		this.logOwningGroup = logOwningGroup;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "log_id", nullable = false)
	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	@Embedded
	public LogCommonDetails getLogCommonDetails() {
		return logCommonDetails;
	}

	public void setLogCommonDetails(LogCommonDetails logCommonDetails) {
		this.logCommonDetails = logCommonDetails;
	}

	@Embedded
	public LogCommonIds getLogCommonIds() {
		return logCommonIds;
	}

	public void setLogCommonIds(LogCommonIds logCommonIds) {
		this.logCommonIds = logCommonIds;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="task_id")
	public Task getParentTask() {
		return parentTask;
	}

	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_owner_id")
	public User getLogOwer() {
		return logOwer;
	}

	public void setLogOwer(User logOwer) {
		this.logOwer = logOwer;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_group_id")
	public Group getLogOwningGroup() {
		return logOwningGroup;
	}

	public void setLogOwningGroup(Group logOwningGroup) {
		this.logOwningGroup = logOwningGroup;
	}

}
