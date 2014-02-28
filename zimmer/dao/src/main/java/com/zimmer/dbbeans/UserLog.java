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
@Table(name="user_log")
public class UserLog implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer logId;
	private LogCommonDetails userLogCommon;
	private LogCommonIds logCommonIds;
	private User logOwner;
	private Group logInGroup;
	private Event parentEvent;
	private Task parentTask;
	private Feed parentFeed;
	
	public UserLog() {
		super();
	}

	public UserLog(LogCommonDetails userLogCommon, LogCommonIds logCommonIds,
			User logOwner, Group logInGroup, Event parentEvent,
			Task parentTask, Feed parentFeed) {
		super();
		this.userLogCommon = userLogCommon;
		this.logCommonIds = logCommonIds;
		this.logOwner = logOwner;
		this.logInGroup = logInGroup;
		this.parentEvent = parentEvent;
		this.parentTask = parentTask;
		this.parentFeed = parentFeed;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="log_id",nullable = false)
	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	@Embedded
	public LogCommonDetails getUserLogCommon() {
		return userLogCommon;
	}

	public void setUserLogCommon(LogCommonDetails userLogCommon) {
		this.userLogCommon = userLogCommon;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_owner_id")
	public User getLogOwner() {
		return logOwner;
	}

	public void setLogOwner(User logOwner) {
		this.logOwner = logOwner;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_group_id")
	public Group getLogInGroup() {
		return logInGroup;
	}

	public void setLogInGroup(Group logInGroup) {
		this.logInGroup = logInGroup;
	}
	
	@Embedded
	public LogCommonIds getLogCommonIds() {
		return logCommonIds;
	}

	public void setLogCommonIds(LogCommonIds logCommonIds) {
		this.logCommonIds = logCommonIds;
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
