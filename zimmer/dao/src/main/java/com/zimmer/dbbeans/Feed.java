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
@Table(name = "feed_details")
public class Feed implements Serializable {

	private static final long serialVersionUID = 1L;

	private String feedId;
	private User owner;
	private String content;
	private Boolean containsAttachments;
	private Boolean containsComments;
	private Boolean containsLikes;
	private Boolean isFeedEdited;
	private String feedType;
	private Date createdAt;
	private Date updatedAt;
	private Boolean isActive;
	private Integer likesCount;
	private Integer commentsCount;
	private Boolean isValid;
	private Set<Comment> feedComments = new HashSet<Comment>();
	private Set<Like> feedLikes = new HashSet<Like>();
	private Set<FileUpload> feedFileUploads = new HashSet<FileUpload>();
	private Set<FeedLog> feedLogs = new HashSet<FeedLog>();
	private Set<TagFeedMap> feedTags = new HashSet<TagFeedMap>();
	private Set<FeedUserMap> feedUsersMap = new HashSet<FeedUserMap>();
	
	public Feed() {
		super();
		this.feedId = UUIDGenerators.generateFeedId();
		this.content = "";
		this.createdAt = new Date();
		this.likesCount = 0;
		this.commentsCount = 0;
		this.isValid = true;
		this.containsAttachments = false;
		this.containsComments = false;
		this.containsLikes = false;
		this.isFeedEdited = false;
		this.isActive = true;
		this.likesCount = 0;
		this.commentsCount = 0;
	}
	
	public Feed(User owner, String content, String feedType) {
		super();
		this.feedId = UUIDGenerators.generateFeedId();
		this.likesCount = 0;
		this.commentsCount = 0;
		this.isValid = true;
		this.containsAttachments = false;
		this.containsComments = false;
		this.containsLikes = false;
		this.isFeedEdited = false;
		this.isActive = true;
		this.likesCount = 0;
		this.commentsCount = 0;
		this.owner = owner;
		this.content = content;
		this.feedType = feedType;
		this.createdAt = new Date();
	}

	public Feed(String feedId, User owner, String content,
			Boolean containsAttachments, Boolean isFeedEdited, String feedType,
			Date createdAt, Date updatedAt, Boolean isActive,
			Integer likesCount, Integer commentsCount, Boolean isValid) {
		super();
		this.feedId = feedId;
		this.owner = owner;
		this.content = content;
		this.containsAttachments = containsAttachments;
		this.isFeedEdited = isFeedEdited;
		this.feedType = feedType;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isActive = isActive;
		this.likesCount = likesCount;
		this.commentsCount = commentsCount;
		this.isValid = isValid;
	}

	@Id
	@Column(name = "feed_id")
	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="feed_owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Column(name = "feed_content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Basic(optional = false)
	@Column(name = "feed_contains_attachments", nullable = false)
	public Boolean getContainsAttachments() {
		return containsAttachments;
	}

	public void setContainsAttachments(Boolean containsAttachments) {
		this.containsAttachments = containsAttachments;
	}

	@Basic(optional = false)
	@Column(name = "feed_contains_comments", nullable = false)
	public Boolean getContainsComments() {
		return containsComments;
	}

	public void setContainsComments(Boolean containsComments) {
		this.containsComments = containsComments;
	}

	@Basic(optional = false)
	@Column(name = "feed_contains_likes", nullable = false)
	public Boolean getContainsLikes() {
		return containsLikes;
	}

	public void setContainsLikes(Boolean containsLikes) {
		this.containsLikes = containsLikes;
	}

	@Basic(optional = false)
	@Column(name = "is_feed_edited", nullable = false)
	public Boolean getIsFeedEdited() {
		return isFeedEdited;
	}

	public void setIsFeedEdited(Boolean isFeedEdited) {
		this.isFeedEdited = isFeedEdited;
	}

	@Basic(optional = false)
	@Column(name = "feed_type", nullable = false)
	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	@Basic(optional = false)
	@Column(name = "feed_created_at", nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "feed_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Basic(optional = false)
	@Column(name = "is_feed_active", nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Basic(optional = false)
	@Column(name = "feed_likes_count", nullable = false)
	public Integer getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(Integer likesCount) {
		this.likesCount = likesCount;
	}

	@Basic(optional = false)
	@Column(name = "feed_comments_count", nullable = false)
	public Integer getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	@Basic(optional = false)
	@Column(name = "is_feed_valid", nullable = false)
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parentFeed")
	public Set<Comment> getFeedComments() {
		return feedComments;
	}

	public void setFeedComments(Set<Comment> feedComments) {
		this.feedComments = feedComments;
	}

	public void addCommentToFeed(Comment comment){
		feedComments.add(comment);
		comment.setParentFeed(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parentFeed")
	public Set<Like> getFeedLikes() {
		return feedLikes;
	}

	public void setFeedLikes(Set<Like> feedLikes) {
		this.feedLikes = feedLikes;
	}

	public void addLikesToFeed(Like like){
		feedLikes.add(like);
		like.setParentFeed(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parentFeed")
	public Set<FileUpload> getFeedFileUploads() {
		return feedFileUploads;
	}

	public void setFeedFileUploads(Set<FileUpload> feedFileUploads) {
		this.feedFileUploads = feedFileUploads;
	}

	public void addFilesToFeed(FileUpload fileUpload){
		feedFileUploads.add(fileUpload);
		fileUpload.setParentFeed(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parentFeed")
	public Set<FeedLog> getFeedLogs() {
		return feedLogs;
	}

	public void setFeedLogs(Set<FeedLog> feedLogs) {
		this.feedLogs = feedLogs;
	}

	public void addLogToFeed(FeedLog feedLog){
		feedLogs.add(feedLog);
		feedLog.setParentFeed(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="feed")
	public Set<TagFeedMap> getFeedTags() {
		return feedTags;
	}

	public void setFeedTags(Set<TagFeedMap> feedTags) {
		this.feedTags = feedTags;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="mappedFeed")
	public Set<FeedUserMap> getFeedUsersMap() {
		return feedUsersMap;
	}

	public void setFeedUsersMap(Set<FeedUserMap> feedUsersMap) {
		this.feedUsersMap = feedUsersMap;
	}

}
