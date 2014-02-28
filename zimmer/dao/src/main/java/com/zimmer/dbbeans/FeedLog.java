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
@Table(name="feed_log")
public class FeedLog implements Serializable{

	private static final long serialVersionUID = 1L;
	private LogCommonDetails feedLogCommonDetails;
	private LogCommonIds logCommonId;
	private Integer logId;
	private User logOwner;
	private Group logOwningGroup;
	private Feed parentFeed;

	public FeedLog() {
		super();
	}

	public FeedLog(LogCommonDetails feedLogCommon, User logOwner,
			Group logOwningGroup, Feed parentFeed) {
		super();
		this.feedLogCommonDetails = feedLogCommon;
		this.logOwner = logOwner;
		this.logOwningGroup = logOwningGroup;
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
	public LogCommonDetails getFeedLogCommonDetails() {
		return feedLogCommonDetails;
	}

	public void setFeedLogCommonDetails(LogCommonDetails feedLogCommon) {
		this.feedLogCommonDetails = feedLogCommon;
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
	public LogCommonIds getLogCommonId() {
		return logCommonId;
	}

	public void setLogCommonId(LogCommonIds logCommonId) {
		this.logCommonId = logCommonId;
	}	

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="parent_feed_id")
	public Feed getParentFeed() {
		return parentFeed;
	}

	public void setParentFeed(Feed parentFeed) {
		this.parentFeed = parentFeed;
	}
}
