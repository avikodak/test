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
@Table(name="group_details")
public class Group implements Serializable{

	private static final long serialVersionUID = 1L;

	private String groupId;
	private String title;
	private String description;
	private String type;
	private User owner;
	private Integer memberCount;
	private Date createdAt;
	private Date updatedAt;
	private Integer totalFeedsCount;
	private Set<GroupLog> groupLogs = new HashSet<GroupLog>();
	private Set<GroupMemberLink> groupMemberLinks = new HashSet<GroupMemberLink>();

	public Group() {
		super();
		this.createdAt = new Date();
		this.groupId = UUIDGenerators.generateGroupId();
		this.memberCount = 0;
		this.totalFeedsCount = 0;
	}

	public Group(String title, String description, String type, User owner) {
		super();
		this.title = title;
		this.description = description;
		this.type = type;
		this.owner = owner;
		this.groupId = UUIDGenerators.generateGroupId();
		this.memberCount = 0;
		this.createdAt = new Date();
		this.totalFeedsCount = 0;
	}

	public Group(String title, String description, String type, User owner,
			Integer memberCount, Date createdAt, Date updatedAt,
			Integer totalFeedsCount) {
		super();
		this.title = title;
		this.description = description;
		this.type = type;
		this.owner = owner;
		this.memberCount = memberCount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.totalFeedsCount = totalFeedsCount;
	}

	@Id
	@Column(name="group_id")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Basic(optional=false)
	@Column(name="group_title",nullable=false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="group_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic(optional=false)
	@Column(name="group_type",nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="group_owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Basic(optional=false)
	@Column(name="group_member_count",nullable=false)
	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	@Basic(optional=false)
	@Column(name="group_created_at",nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="group_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Basic(optional=false)
	@Column(name="group_feeds_count")
	public Integer getTotalFeedsCount() {
		return totalFeedsCount;
	}

	public void setTotalFeedsCount(Integer totalFeedsCount) {
		this.totalFeedsCount = totalFeedsCount;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="owningGroup")
	public Set<GroupLog> getGroupLogs() {
		return groupLogs;
	}

	public void setGroupLogs(Set<GroupLog> groupLogs) {
		this.groupLogs = groupLogs;
	}

	public void addLogToGroupLogs(GroupLog groupLog){
		groupLogs.add(groupLog);
		groupLog.setOwningGroup(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="group")
	public Set<GroupMemberLink> getGroupMembers() {
		return groupMemberLinks;
	}

	public void setGroupMembers(Set<GroupMemberLink> groupMemberLinks) {
		this.groupMemberLinks = groupMemberLinks;
	}
}
