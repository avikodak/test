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
@Table(name="group_log")
public class GroupLog implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer logId;
	private Group owningGroup;
	private LogCommonDetails groupLog;
	private LogCommonIds commonId;
	private User logOwner;
	private Event parentEvent;
	private Task parentTask;
	private Feed parentFeed;
	
	public GroupLog() {
		super();
	}
	
	public GroupLog(Group owningGroup,
			LogCommonDetails groupLog, LogCommonIds commonId, User logOwner,
			Event parentEvent, Task parentTask, Feed parentFeed) {
		super();
		this.owningGroup = owningGroup;
		this.groupLog = groupLog;
		this.commonId = commonId;
		this.logOwner = logOwner;
		this.parentEvent = parentEvent;
		this.parentTask = parentTask;
		this.parentFeed = parentFeed;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="log_id")
	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_group_id")
	public Group getOwningGroup() {
		return owningGroup;
	}

	public void setOwningGroup(Group owningGroup) {
		this.owningGroup = owningGroup;
	}

	@Embedded
	public LogCommonDetails getGroupLog() {
		return groupLog;
	}

	public void setGroupLog(LogCommonDetails groupLog) {
		this.groupLog = groupLog;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_owner_id")
	public User getLogOwner() {
		return logOwner;
	}

	public void setLogOwner(User logOwner) {
		this.logOwner = logOwner;
	}

	@Embedded
	public LogCommonIds getCommonId() {
		return commonId;
	}

	public void setCommonId(LogCommonIds commonId) {
		this.commonId = commonId;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_event_id")
	public Event getParentEvent() {
		return parentEvent;
	}

	public void setParentEvent(Event parentEvent) {
		this.parentEvent = parentEvent;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_task_id")
	public Task getParentTask() {
		return parentTask;
	}

	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_feed_id")
	public Feed getParentFeed() {
		return parentFeed;
	}

	public void setParentFeed(Feed parentFeed) {
		this.parentFeed = parentFeed;
	}	
}
