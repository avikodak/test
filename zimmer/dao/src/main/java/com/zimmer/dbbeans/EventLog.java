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
@Table(name="event_log")
public class EventLog implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer logId;
	private LogCommonDetails eventLogCommon;
	private User logOwner;
	private Group logOwningGroup;
	private LogCommonIds commonIds;
	private Event parentEvent;
	
	public EventLog() {
		super();
	}
	
	public EventLog(LogCommonDetails eventLogCommon, User logOwner,
			Group logOwningGroup, LogCommonIds commonIds, Event parentEvent) {
		super();
		this.eventLogCommon = eventLogCommon;
		this.logOwner = logOwner;
		this.logOwningGroup = logOwningGroup;
		this.commonIds = commonIds;
		this.parentEvent = parentEvent;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "log_id", nullable = false)
	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	@Embedded
	public LogCommonDetails getEventLogCommon() {
		return eventLogCommon;
	}

	public void setEventLogCommon(LogCommonDetails eventLogCommon) {
		this.eventLogCommon = eventLogCommon;
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
	public Group getLogOwningGroup() {
		return logOwningGroup;
	}

	public void setLogOwningGroup(Group logOwningGroup) {
		this.logOwningGroup = logOwningGroup;
	}

	@Embedded
	public LogCommonIds getCommonIds() {
		return commonIds;
	}

	public void setCommonIds(LogCommonIds commonIds) {
		this.commonIds = commonIds;
	}
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="parent_event_id")
	public Event getParentEvent() {
		return parentEvent;
	}

	public void setParentEvent(Event parentEvent) {
		this.parentEvent = parentEvent;
	}
}
