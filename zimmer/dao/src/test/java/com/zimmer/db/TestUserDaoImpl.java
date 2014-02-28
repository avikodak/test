package com.zimmer.db;

import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zimmer.dbbeans.Address;
import com.zimmer.dbbeans.Comment;
import com.zimmer.dbbeans.Company;
import com.zimmer.dbbeans.Event;
import com.zimmer.dbbeans.EventLog;
import com.zimmer.dbbeans.Feed;
import com.zimmer.dbbeans.FeedLog;
import com.zimmer.dbbeans.FileUpload;
import com.zimmer.dbbeans.Group;
import com.zimmer.dbbeans.GroupLog;
import com.zimmer.dbbeans.GroupMemberLink;
import com.zimmer.dbbeans.Like;
import com.zimmer.dbbeans.LogCommonDetails;
import com.zimmer.dbbeans.Tag;
import com.zimmer.dbbeans.TagEventMap;
import com.zimmer.dbbeans.TagFeedMap;
import com.zimmer.dbbeans.TagTaskMap;
import com.zimmer.dbbeans.Task;
import com.zimmer.dbbeans.TaskLog;
import com.zimmer.dbbeans.User;
import com.zimmer.dbbeans.UserDetailsCommon;
import com.zimmer.dbbeans.UserEmailAddress;
import com.zimmer.dbbeans.UserLog;
import com.zimmer.dbbeans.UserPhoneContacts;
import com.zimmer.dbbeans.UserWebsite;

@Component
public class TestUserDaoImpl {
	AbstractXmlApplicationContext context;
	static protected AbstractUserDao userDao;
	static protected AbstractCompanyDao companyDao;
	static protected AbstractTagDao tagDao;
	
	@BeforeMethod
	public void setup() throws Exception {
		context = new ClassPathXmlApplicationContext("/applicationContext.xml");
	}

	@AfterMethod
	public void destroy() {
		context.destroy();
	}

	public static AbstractUserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public  void setUserDao(@Qualifier("userDao") AbstractUserDao userDao) {
		TestUserDaoImpl.userDao = userDao;
	}

	public static AbstractCompanyDao getCompanyDao() {
		return companyDao;
	}

	@Autowired
	public  void setDao(@Qualifier("companyDao") AbstractCompanyDao companyDao) {
		TestUserDaoImpl.companyDao = companyDao;
	}
	
	public static AbstractTagDao getTagDao() {
		return tagDao;
	}

	@Autowired
	public void setTagDao(@Qualifier("tagDaoImpl") AbstractTagDao tagDao) {
		TestUserDaoImpl.tagDao = tagDao;
	}

	@BeforeClass
	public void setupClass() throws Exception {

	} 

	@Test
	public void testUserCreateAndGet() throws Exception {
		Assert.assertNotNull(userDao);
		Integer userCount = new Integer(0);
		Date date = new Date();
		Address companyAddress = new Address("test","test1","test2","test3","test4",1234L);
		Company company = new Company("test1234","test123",userCount,date,date,true);
		company.setCompanyAddress(companyAddress);


		LogCommonDetails logCommon = new LogCommonDetails(true,date,date,"kkk","Company Log");
		String companyId = companyDao.createCompany(company);
		Assert.assertNotNull(companyId);
		Assert.assertNotNull(companyDao.getCompanyById(companyId));
		User newUser = new User(company, "test", "test", TimeZone.getDefault(), "test", "test", "test", "test", "test", companyAddress, true, true, date, date, date, "norm");
		newUser.setUserCompany(companyDao.getCompanyById(companyId));
		//String userId = userDao.createUser(newUser);

		UserDetailsCommon userDetailsCommon = new UserDetailsCommon();
		userDetailsCommon.setType("PRIMARY");
		userDetailsCommon.setIsActive(true);

		UserWebsite oneWebsiteUser = new UserWebsite();
		oneWebsiteUser.setCommonDetails(userDetailsCommon);
		oneWebsiteUser.setWebsiteUrl("test");
		newUser.addUserWebsite(oneWebsiteUser);

		UserPhoneContacts phoneContacts = new UserPhoneContacts();
		phoneContacts.setCommonDetails(userDetailsCommon);
		phoneContacts.setUserPhoneNumber("8050658991");
		newUser.addUserPhoneContact(phoneContacts);

		UserEmailAddress emailAddress = new UserEmailAddress();
		emailAddress.setCommonDetails(userDetailsCommon);
		emailAddress.setEmailAddress("avikodak");
		newUser.addUserEmailAddress(emailAddress);


		Group group = new Group("Test", "Test group", "open", newUser, userCount, date,date, userCount);
		group.setGroupId("1");
		newUser.addOwningGroup(group);

		UserLog userLog = new UserLog(logCommon, null, newUser, group, null, null, null);
		newUser.addLogToUserLogs(userLog);


		Double percentage = new Double("90");
		Task task = new Task(newUser, "test", "test", "test", "opened", "test", percentage, true, true, date, date, date);
		task.setTaskId("1");
		newUser.addOwningTask(task);

		TaskLog taskLog = new TaskLog(logCommon, null, task, newUser, group);
		task.addLogToTask(taskLog);

		Event event = new Event("1",newUser,"test","test",date,date,userCount,userCount,userCount,date,date,false,false,true,true,"test location");
		newUser.addOwningEvents(event);
		event.setContainsAttachments(true);
		event.setContainsComments(true);
		event.setContainsLikes(true);
		EventLog eventLog = new EventLog(logCommon, newUser, group, null, event);
		event.addLogToEventLog(eventLog);



		Feed feed = new Feed("1", newUser, "test", false, false, "test", date, date, true, userCount, userCount, true);
		newUser.addUserPublishedFeed(feed);
		feed.setContainsAttachments(true);
		feed.setContainsComments(true);
		feed.setContainsLikes(true);
		FeedLog feedLog = new FeedLog(logCommon, newUser, group, feed);
		feed.addLogToFeed(feedLog);
		GroupLog groupFeedLog = new GroupLog(group, logCommon, null, newUser, null, null, feed);
		GroupLog groupFeedLog2 = new GroupLog(group, logCommon, null, newUser, null, null, feed);
		group.addLogToGroupLogs(groupFeedLog);
		group.addLogToGroupLogs(groupFeedLog2);


		Comment comment = new Comment("1", newUser, "test", false, false, false, date, date, feed);
		comment.setCommentFor("feed");
		feed.addCommentToFeed(comment);
		newUser.addCommentsToUserComments(comment);


		//feedLog.getLogCommonId().setParentComment(comment);
		//feed.addLogToFeed(feedLog);

		Like like = new Like("1", newUser, true, date, date, feed);
		like.setLikeFor("feed");
		feed.addLikesToFeed(like);
		newUser.addLikeToUserLikes(like);

		FileUpload fileUpload = new FileUpload("1", "http://google.com", percentage, "jpeg", date, date, newUser, true, feed,newUser);
		feed.addFilesToFeed(fileUpload);
		newUser.addFilesToUser(fileUpload);

		userDao.createUser(newUser);
		GroupMemberLink groupMemberLink = new GroupMemberLink("admin", date, null, newUser, true, group, newUser);
		
		
		Tag tag = new Tag("1", "Test", true, newUser, null, date, date, "feed");
		tagDao.createTag(tag);
		
		TagFeedMap tagFeedMap = new TagFeedMap(date, null, newUser, true, tag, feed);
		tagDao.createLinkTagFeed(tagFeedMap);
		
		TagTaskMap tagTaskMap = new TagTaskMap(date, null, newUser, true, tag, task);
		tagDao.createLinkTagTask(tagTaskMap);
	
		TagEventMap tagEventMap = new TagEventMap(date, null, newUser, true, tag, event);
		tagDao.createLinkTagEvent(tagEventMap);
		
		
	}

}
