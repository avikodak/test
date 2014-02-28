package com.zimmer.dbbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zimmer.utils.UUIDGenerators;

@Entity
@Table(name="user_details")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private Company userCompany;
	private String userId;
	private String username;
	private String password;
	private TimeZone timezone;
	private String firstName;
	private String lastName;
	private String profilePicture;
	private String designation;
	private String skills;
	private Address userAddress;
	private Boolean isValid;
	private Boolean isActive;
	private Date createdAt;
	private Date updatedAt;
	private Date lastLoginAt;
	private String userRole;
	private Set<GroupMemberLink> userGroups = new HashSet<GroupMemberLink>();
	private Set<FileUpload> userFileUploads = new HashSet<FileUpload>();
	private Set<Comment> userComments = new HashSet<Comment>();
	private Set<Like> userLikes = new HashSet<Like>();
	private Set<Feed> publishedFeeds = new HashSet<Feed>();
	private Set<Event> owningEvents = new HashSet<Event>();
	private Set<Group> owningGroups = new HashSet<Group>();
	private Set<Task> owningTasks = new HashSet<Task>();
	private Set<UserWebsite> userWebsites = new HashSet<UserWebsite>();
	private Set<UserEmailAddress> userEmailAddresses = new HashSet<UserEmailAddress>();
	private Set<UserPhoneContacts> userPhoneContacts = new HashSet<UserPhoneContacts>();
	private Set<UserLog> userLogs = new HashSet<UserLog>();
	private Set<FeedUserMap> userFeedsMap = new HashSet<FeedUserMap>();
	private Set<EventUserMap> userEventsMap = new HashSet<EventUserMap>();
	private Set<TaskUserMap> userTaskMap = new HashSet<TaskUserMap>();
	private Set<UserContacts> userContacts = new HashSet<UserContacts>();

	public User() {
		this.createdAt = new Date();
		this.isActive = true;
		this.isValid = true;
		this.userId = UUIDGenerators.generateUserId();
		this.userRole = "NORM_USER";
	}

	public User(Company userCompany, String username, String password,
			String firstName, String lastName, String profilePicture,
			String designation, String skills, Address userAddress,
			String userRole) {
		super();
		this.userCompany = userCompany;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePicture = profilePicture;
		this.designation = designation;
		this.skills = skills;
		this.userAddress = userAddress;
		this.userRole = userRole;
		this.createdAt = new Date();
		this.isActive = true;
		this.isValid = true;
		this.userId = UUIDGenerators.generateUserId();
	}

	public User(Company userCompany, String username, String password,
			String firstName, String lastName, String profilePicture,
			String designation, String skills, Address userAddress) {
		super();
		this.userCompany = userCompany;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePicture = profilePicture;
		this.designation = designation;
		this.skills = skills;
		this.userAddress = userAddress;
		this.createdAt = new Date();
		this.isActive = true;
		this.isValid = true;
		this.userId = UUIDGenerators.generateUserId();
	}

	public User(Company userCompany, String username, String password,
			TimeZone timezone, String firstName, String lastName,
			String profilePicture, String designation, String skills,
			Address userAddress, Boolean isValid, Boolean isActive,
			Date createdAt, Date updatedAt, Date lastLoginAt, String userRole) {
		super();
		this.userCompany = userCompany;
		this.username = username;
		this.password = password;
		this.timezone = timezone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePicture = profilePicture;
		this.designation = designation;
		this.skills = skills;
		this.userAddress = userAddress;
		this.isValid = isValid;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.lastLoginAt = lastLoginAt;
		this.userRole = userRole;
		this.userId = UUIDGenerators.generateUserId();
	}

	@ManyToOne
	@JoinColumn(name="company_id",nullable=false)
	public Company getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(Company userCompany) {
		this.userCompany = userCompany;
		this.userCompany.getCompanyUsers().add(this);
	}

	@Id
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Basic(optional=false)
	@Column(name="username",nullable=false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic(optional=false)
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic(optional=false)
	@Column(name="timezone",nullable=false)
	public TimeZone getTimezone() {
		return timezone;
	}

	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}

	@Basic(optional=false)
	@Column(name="first_name",nullable=false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Basic(optional=false)
	@Column(name="last_name",nullable=false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="profile_picture")
	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Basic(optional=false)
	@Column(name="designation",nullable=false)
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name="skills")
	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="addressLine1",column=@Column(name="user_address_line1")),
		@AttributeOverride(name="addressLine2",column=@Column(name="user_address_line2")),
		@AttributeOverride(name="city",column=@Column(name="user_city")),
		@AttributeOverride(name="state",column=@Column(name="user_state")),
		@AttributeOverride(name="country",column=@Column(name="user_country")),
		@AttributeOverride(name="zipcode",column=@Column(name="user_zipcode"))
	})
	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address address) {
		this.userAddress = address;
	}

	@Basic(optional=false)
	@Column(name="is_user_valid",nullable=false)
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	@Basic(optional=false)
	@Column(name="is_user_active",nullable=false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Basic(optional=false)
	@Column(name="user_created_at",nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="user_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name="users_last_login_date")
	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	@Basic(optional=false)
	@Column(name="user_role",nullable=false)
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.ALL},mappedBy="fileOwner")
	public Set<FileUpload> getUserFileUploads() {
		return userFileUploads;
	}

	public void setUserFileUploads(Set<FileUpload> userFileUploads) {
		this.userFileUploads = userFileUploads;
	}

	public void addFilesToUser(FileUpload fileUpload){
		userFileUploads.add(fileUpload);
		fileUpload.setFileOwner(this);
	}

	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.ALL},mappedBy="owner")
	public Set<Comment> getUserComments() {
		return userComments;
	}

	public void setUserComments(Set<Comment> userComments) {
		this.userComments = userComments;
	}

	public void addCommentsToUserComments(Comment comment){
		userComments.add(comment);
		comment.setOwner(this);
	}

	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.ALL},mappedBy="owner")
	public Set<Like> getUserLikes() {
		return userLikes;
	}

	public void setUserLikes(Set<Like> userLikes) {
		this.userLikes = userLikes;
	}

	public void addLikeToUserLikes(Like like){
		userLikes.add(like);
		like.setOwner(this);
	}

	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.ALL},mappedBy="owner")
	public Set<Feed> getPublishedFeeds() {
		return publishedFeeds;
	}

	public void setPublishedFeeds(Set<Feed> publishedFeeds) {
		this.publishedFeeds = publishedFeeds;
	}

	public void addUserPublishedFeed(Feed feed) {
		publishedFeeds.add(feed);
		feed.setOwner(this);
	}

	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.ALL},mappedBy="owner")
	public Set<Event> getOwningEvents() {
		return owningEvents;
	}

	public void setOwningEvents(Set<Event> owningEvents) {
		this.owningEvents = owningEvents;
	}

	public void addOwningEvents(Event event){
		owningEvents.add(event);
		event.setOwner(this);
	}

	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.ALL},mappedBy="owner")
	public Set<Task> getOwningTasks() {
		return owningTasks;
	}

	public void setOwningTasks(Set<Task> owningTasks) {
		this.owningTasks = owningTasks;
	}

	public void addOwningTask(Task task){
		owningTasks.add(task);
		task.setOwner(this);
	}

	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.ALL},mappedBy="owner")
	public Set<Group> getOwningGroups() {
		return owningGroups;
	}

	public void setOwningGroups(Set<Group> owningGroups) {
		this.owningGroups = owningGroups;
	}

	public void addOwningGroup(Group group){
		owningGroups.add(group);
		group.setOwner(this);
	}

	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.ALL},mappedBy="commonDetails.user")
	public Set<UserWebsite> getUserWebsites() {
		return userWebsites;
	}

	public void setUserWebsites(Set<UserWebsite> userWebsites) {
		this.userWebsites = userWebsites;
	}

	public void addUserWebsite(UserWebsite website){
		userWebsites.add(website);
		website.getCommonDetails().setUser(this);
	}

	@OneToMany(cascade={CascadeType.ALL},mappedBy="commonDetails.user",fetch=FetchType.LAZY)
	public Set<UserEmailAddress> getUserEmailAddresses() {
		return userEmailAddresses;
	}

	public void setUserEmailAddresses(Set<UserEmailAddress> userEmailAddresses) {
		this.userEmailAddresses = userEmailAddresses;
	}

	public void addUserEmailAddress(UserEmailAddress emailAddress){
		userEmailAddresses.add(emailAddress);
		emailAddress.getCommonDetails().setUser(this);
	}

	@OneToMany(cascade={CascadeType.ALL},mappedBy="commonDetails.user",fetch=FetchType.LAZY)
	public Set<UserPhoneContacts> getUserPhoneContacts() {
		return userPhoneContacts;
	}

	public void setUserPhoneContacts(Set<UserPhoneContacts> userPhoneContacts) {
		this.userPhoneContacts = userPhoneContacts;
	}

	public void addUserPhoneContact(UserPhoneContacts phoneContacts){
		userPhoneContacts.add(phoneContacts);
		phoneContacts.getCommonDetails().setUser(this);
	}

	@OneToMany(cascade={CascadeType.ALL},mappedBy="logOwner",fetch=FetchType.LAZY)
	public Set<UserLog> getUserLogs() {
		return userLogs;
	}

	public void setUserLogs(Set<UserLog> userLogs) {
		this.userLogs = userLogs;
	}

	public void addLogToUserLogs(UserLog userLog){
		userLogs.add(userLog);
		userLog.setLogOwner(this);
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="member")
	public Set<GroupMemberLink> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<GroupMemberLink> userGroups) {
		this.userGroups = userGroups;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="mappedUser")
	public Set<FeedUserMap> getUserFeedsMap() {
		return userFeedsMap;
	}

	public void setUserFeedsMap(Set<FeedUserMap> userFeedsMap) {
		this.userFeedsMap = userFeedsMap;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="mappedUser")
	public Set<EventUserMap> getUserEventsMap() {
		return userEventsMap;
	}

	public void setUserEventsMap(Set<EventUserMap> userEventsMap) {
		this.userEventsMap = userEventsMap;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="mappedUser")
	public Set<TaskUserMap> getUserTaskMap() {
		return userTaskMap;
	}

	public void setUserTaskMap(Set<TaskUserMap> userTaskMap) {
		this.userTaskMap = userTaskMap;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="owner")
	public Set<UserContacts> getUserContacts() {
		return userContacts;
	}

	public void setUserContacts(Set<UserContacts> userContacts) {
		this.userContacts = userContacts;
	}
}
