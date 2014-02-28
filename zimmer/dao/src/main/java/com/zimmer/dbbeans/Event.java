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
@Table(name = "event_details")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	private String eventId;
	private User owner;
	private String title;
	private String description;
	private Date eventStart;
	private Date eventEnd;
	private Integer invitedCount;
	private Integer attendingCount;
	private Integer mayBeCount;
	private Date createdAt;
	private Date updatedAt;
	private Boolean isEdited;
	private Boolean isPublicEvent;
	private Boolean isActive;
	private Boolean isSelfRemainder;
	private String locationDescription;
	private Boolean containsAttachments;
	private Boolean containsComments;
	private Boolean containsLikes;
	private Set<Comment> eventComments = new HashSet<Comment>();
	private Set<Like> eventLikes = new HashSet<Like>();
	private Set<FileUpload> eventFileUploads = new HashSet<FileUpload>();
	private Set<EventLog> eventLogs = new HashSet<EventLog>();
	private Set<TagEventMap> eventsTags = new HashSet<TagEventMap>();
	private Set<EventUserMap> eventMappedUsers = new HashSet<EventUserMap>();

	public Event() {
		super();
		this.eventId = UUIDGenerators.generateEventId();
		this.invitedCount = 0;
		this.attendingCount = 0;
		this.mayBeCount = 0;
		this.createdAt = new Date();
		this.isActive = true;
	}

	public Event(String eventId, User owner, String title, String description,
			Date eventStart, Date eventEnd, Integer invitedCount,
			Integer attendingCount, Integer mayBeCount, Date createdAt,
			Date updatedAt, Boolean isEdited, Boolean isPublicEvent,
			Boolean isActive, Boolean isSelfRemainder,
			String locationDescription) {
		super();
		this.eventId = eventId;
		this.owner = owner;
		this.title = title;
		this.description = description;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.invitedCount = invitedCount;
		this.attendingCount = attendingCount;
		this.mayBeCount = mayBeCount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isEdited = isEdited;
		this.isPublicEvent = isPublicEvent;
		this.isActive = isActive;
		this.isSelfRemainder = isSelfRemainder;
		this.locationDescription = locationDescription;
	}

	@Id
	@Column(name = "event_id")
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "event_owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Basic(optional = false)
	@Column(name = "event_title", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "event_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic(optional = false)
	@Column(name = "event_start", nullable = false)
	public Date getEventStart() {
		return eventStart;
	}

	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}

	@Basic(optional = false)
	@Column(name = "event_end", nullable = false)
	public Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	@Basic(optional = false)
	@Column(name = "event_invited_count", nullable = false)
	public Integer getInvitedCount() {
		return invitedCount;
	}

	public void setInvitedCount(Integer invitedCount) {
		this.invitedCount = invitedCount;
	}

	@Basic(optional = false)
	@Column(name = "event_attending_count", nullable = false)
	public Integer getAttendingCount() {
		return attendingCount;
	}

	public void setAttendingCount(Integer attendingCount) {
		this.attendingCount = attendingCount;
	}

	@Basic(optional = false)
	@Column(name = "event_may_be_count", nullable = false)
	public Integer getMayBeCount() {
		return mayBeCount;
	}

	public void setMayBeCount(Integer mayBeCount) {
		this.mayBeCount = mayBeCount;
	}

	@Basic(optional = false)
	@Column(name = "event_created_at", nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "event_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Basic(optional = false)
	@Column(name = "event_edited_status", nullable = false)
	public Boolean getIsEdited() {
		return isEdited;
	}

	public void setIsEdited(Boolean isEdited) {
		this.isEdited = isEdited;
	}

	@Basic(optional = false)
	@Column(name = "is_event_active", nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Basic(optional = false)
	@Column(name = "is_event_to_self", nullable = false)
	public Boolean getIsSelfRemainder() {
		return isSelfRemainder;
	}

	public void setIsSelfRemainder(Boolean isSelfRemainder) {
		this.isSelfRemainder = isSelfRemainder;
	}

	@Column(name = "event_location")
	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	@Basic(optional = false)
	@Column(name = "is_public_event")
	public Boolean getIsPublicEvent() {
		return isPublicEvent;
	}

	public void setIsPublicEvent(Boolean isPublicEvent) {
		this.isPublicEvent = isPublicEvent;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentEvent")
	public Set<Comment> getEventComments() {
		return eventComments;
	}

	public void setEventComments(Set<Comment> eventComments) {
		this.eventComments = eventComments;
	}

	public void addCommentsToEvent(Comment comment) {
		eventComments.add(comment);
		comment.setParentEvent(this);
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentEvent")
	public Set<Like> getEventLikes() {
		return eventLikes;
	}

	public void setEventLikes(Set<Like> eventLikes) {
		this.eventLikes = eventLikes;
	}

	public void addLikesToEvent(Like like) {
		eventLikes.add(like);
		like.setParentEvent(this);
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentEvent")
	public Set<FileUpload> getEventFileUploads() {
		return eventFileUploads;
	}

	public void setEventFileUploads(Set<FileUpload> eventFileUploads) {
		this.eventFileUploads = eventFileUploads;
	}

	public void addFileUploadsToEvent(FileUpload eventFileUpload) {
		this.eventFileUploads.add(eventFileUpload);
		eventFileUpload.setParentEvent(this);
	}

	@Basic(optional = false)
	@Column(name = "event_contains_attachments")
	public Boolean getContainsAttachments() {
		return containsAttachments;
	}

	public void setContainsAttachments(Boolean containsAttachments) {
		this.containsAttachments = containsAttachments;
	}

	@Basic(optional = false)
	@Column(name = "event_contains_comments")
	public Boolean getContainsComments() {
		return containsComments;
	}

	public void setContainsComments(Boolean containsComments) {
		this.containsComments = containsComments;
	}

	@Basic(optional = false)
	@Column(name = "event_contains_likes")
	public Boolean getContainsLikes() {
		return containsLikes;
	}

	public void setContainsLikes(Boolean containsLikes) {
		this.containsLikes = containsLikes;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentEvent")
	public Set<EventLog> getEventLogs() {
		return eventLogs;
	}

	public void setEventLogs(Set<EventLog> eventLogs) {
		this.eventLogs = eventLogs;
	}

	public void addLogToEventLog(EventLog eventLog) {
		eventLogs.add(eventLog);
		eventLog.setParentEvent(this);
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "event")
	public Set<TagEventMap> getEventsTags() {
		return eventsTags;
	}

	public void setEventsTags(Set<TagEventMap> eventsTags) {
		this.eventsTags = eventsTags;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "mappedEvent")
	public Set<EventUserMap> getEventMappedUsers() {
		return eventMappedUsers;
	}

	public void setEventMappedUsers(Set<EventUserMap> eventMappedUsers) {
		this.eventMappedUsers = eventMappedUsers;
	}

}
