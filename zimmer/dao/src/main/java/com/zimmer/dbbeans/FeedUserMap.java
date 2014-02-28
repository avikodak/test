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
@Table(name="feed_user_map")
public class FeedUserMap implements Serializable{

	private static final long serialVersionUID = 1L;

	@Embeddable
	@SuppressWarnings("unused")
	private static class Key implements Serializable {
		private static final long serialVersionUID = 1L;
		private String feedId;
		private String userId;

		public Key() {
			super();
		}

		@Basic(optional=false)
		@Column(name="mapped_feed_id")
		public String getFeedId() {
			return feedId;
		}

		public void setFeedId(String feedId) {
			this.feedId = feedId;
		}

		@Basic(optional=false)
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
			+ ((feedId == null) ? 0 : feedId.hashCode());
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
			if (feedId == null) {
				if (other.feedId != null)
					return false;
			} else if (!feedId.equals(other.feedId))
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
	private Feed mappedFeed;
	private User mappedUser;

	public FeedUserMap() {
		super();
		this.createdAt = new Date();
	}

	public FeedUserMap(Date createdAt, User owner, Feed mappedFeed,
			User mappedUser) {
		super();
		this.createdAt = createdAt;
		this.owner = owner;
		this.mappedFeed = mappedFeed;
		this.mappedUser = mappedUser;
		this.linkId.feedId = mappedFeed.getFeedId();
		this.linkId.userId = mappedUser.getUserId();
		mappedFeed.getFeedUsersMap().add(this);
		mappedUser.getUserFeedsMap().add(this);
	}

	public FeedUserMap(User owner, Feed mappedFeed, User mappedUser) {
		super();
		this.owner = owner;
		this.createdAt = new Date();
		this.mappedFeed = mappedFeed;
		this.mappedUser = mappedUser;
		this.linkId.feedId = mappedFeed.getFeedId();
		this.linkId.userId = mappedUser.getUserId();
		mappedFeed.getFeedUsersMap().add(this);
		mappedUser.getUserFeedsMap().add(this);
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
	@Column(name="feed_user_map_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="feed_user_map_owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="mapped_feed_id",insertable=false,updatable=false)
	public Feed getMappedFeed() {
		return mappedFeed;
	}

	public void setMappedFeed(Feed mappedFeed) {
		this.mappedFeed = mappedFeed;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="mapped_user_id",insertable=false,updatable=false)
	public User getMappedUser() {
		return mappedUser;
	}

	public void setMappedUser(User mappedUser) {
		this.mappedUser = mappedUser;
	}
}
