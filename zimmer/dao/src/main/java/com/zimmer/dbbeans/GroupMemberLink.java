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
@Table(name="group_member_map")
public class GroupMemberLink implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Embeddable
	@SuppressWarnings("unused")
	private static class key implements Serializable{
		private static final long serialVersionUID = 1L;
		private String groupId;
		private String userId;

		public key() {
			super();
		}

		@Basic(optional = false)
		@Column(name = "link_group_id")
		public String getGroupId() {
			return groupId;
		}

		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}

		@Basic(optional = false)
		@Column(name = "link_member_id")
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
			+ ((groupId == null) ? 0 : groupId.hashCode());
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
			key other = (key) obj;
			if (groupId == null) {
				if (other.groupId != null)
					return false;
			} else if (!groupId.equals(other.groupId))
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
	private key linkId = new key();
	private String memberRoleInGroup;
	private Date createdAt;
	private Date updatedAt;
	private User addedBy;
	private Boolean isActive;
	private Group group;
	private User member;

	public GroupMemberLink() {
		this.createdAt = new Date();
		this.isActive = true;
	}

	public GroupMemberLink(String memberRoleInGroup, Date createdAt,
			Date updatedAt, User addedBy, Boolean isActive, Group group,
			User member) {
		super();
		this.memberRoleInGroup = memberRoleInGroup;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.addedBy = addedBy;
		this.isActive = isActive;
		this.group = group;
		this.member = member;
		this.linkId.groupId = group.getGroupId();
		this.linkId.userId = member.getUserId();
		group.getGroupMembers().add(this);
		member.getUserGroups().add(this);
	}

	public GroupMemberLink(Group group, User member,User memberAddedBy) {
		this.group = group;
		this.member = member;
		this.createdAt = new Date();
		this.linkId.groupId = group.getGroupId();
		this.linkId.userId = member.getUserId();
		this.addedBy = memberAddedBy;
		group.getGroupMembers().add(this);
		member.getUserGroups().add(this);
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
	public key getLinkId() {
		return linkId;
	}

	public void setLinkId(key linkId) {
		this.linkId = linkId;
	}

	@Basic(optional = false)
	@Column(name = "member_role")
	public String getMemberRoleInGroup() {
		return memberRoleInGroup;
	}

	public void setMemberRoleInGroup(String memberRoleInGroup) {
		this.memberRoleInGroup = memberRoleInGroup;
	}

	@Basic(optional = false)
	@Column(name = "link_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "link_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne(optional=false,cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="added_by_user_id")
	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}

	@Basic(optional=false)
	@Column(name="is_link_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToOne(optional=false,cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="link_group_id",insertable=false,updatable=false)
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@ManyToOne(optional = false,cascade={CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name="link_member_id",insertable=false,updatable=false)
	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

}
