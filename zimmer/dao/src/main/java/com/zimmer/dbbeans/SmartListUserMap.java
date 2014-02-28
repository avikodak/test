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
@Table(name = "smartlist_user_map")
public class SmartListUserMap implements Serializable {

	private static final long serialVersionUID = 1L;

	@Embeddable
	@SuppressWarnings("unused")
	private static class Key implements Serializable {
		private static final long serialVersionUID = 1L;
		private String smartListId;
		private String userId;

		public Key() {
			super();
		}

		@Basic(optional = false)
		@Column(name="mapped_smartlist_id")
		public String getSmartListId() {
			return smartListId;
		}

		public void setSmartListId(String smartListId) {
			this.smartListId = smartListId;
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
					+ ((smartListId == null) ? 0 : smartListId.hashCode());
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
			if (smartListId == null) {
				if (other.smartListId != null)
					return false;
			} else if (!smartListId.equals(other.smartListId))
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
	private Boolean isActive;
	private Date updatedAt;
	private SmartList smartList;
	private User userInSmartList;
	
	
	public SmartListUserMap() {
		super();
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
	@Column(name="smartlist_user_map_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Basic(optional = false)
	@Column(name="is_smarlist_user_map_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name="smartlist_user_map_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="mapped_smartlist_id",insertable=false,updatable=false)
	public SmartList getSmartList() {
		return smartList;
	}

	public void setSmartList(SmartList smartList) {
		this.smartList = smartList;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="mapped_user_id",insertable=false,updatable=false)
	public User getUserInSmartList() {
		return userInSmartList;
	}

	public void setUserInSmartList(User userInSmartList) {
		this.userInSmartList = userInSmartList;
	}
}
