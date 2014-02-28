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
import com.zimmer.dbbeans.Company;
import com.zimmer.dbbeans.Group;
import com.zimmer.dbbeans.User;

@Component
public class TestGroupDaoImpl {

	AbstractXmlApplicationContext context;
	static protected AbstractGroupDao groupDao;
	static protected AbstractUserDao userDao;
	static protected AbstractCompanyDao companyDao;

	@BeforeMethod
	public void setup() throws Exception {
		context = new ClassPathXmlApplicationContext("/applicationContext.xml");
	}

	@AfterMethod
	public void destroy() {
		context.destroy();
	}

	@BeforeClass
	public void setupClass() throws Exception {

	}

	public static AbstractGroupDao getGroupDao() {
		return groupDao;
	}

	@Autowired
	public void setGroupDao(@Qualifier("groupDao") AbstractGroupDao groupDao) {
		TestGroupDaoImpl.groupDao = groupDao;
	} 

	public static AbstractUserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(@Qualifier("userDao") AbstractUserDao userDao) {
		TestGroupDaoImpl.userDao = userDao;
	}

	public static AbstractCompanyDao getCompanyDao() {
		return companyDao;
	}

	@Autowired
	public void setCompanyDao(@Qualifier("companyDao") AbstractCompanyDao companyDao) {
		TestGroupDaoImpl.companyDao = companyDao;
	}

	@Test
	public void testGroupCreateAndGet() throws Exception{
		/*Assert.assertNotNull(userDao);
		Assert.assertNotNull(groupDao);
		Integer userCount = new Integer(0);
		Date date = new Date();
		Address companyAddress = new Address("test","test1","test2","test3","test4",1234L);
		Company company = new Company("test1234","test123",userCount,date,date,true);
		company.setCompanyAddress(companyAddress);
		String companyId = companyDao.createCompany(company);
		Assert.assertNotNull(companyId);
		Assert.assertNotNull(companyDao.getCompanyById(companyId));
		User owner = new User(company, "test", "test", TimeZone.getDefault(), "test", "test", "test", "test", "test", companyAddress, true, true, date, date, date, "norm");
		company.getCompanyUsers().add(owner);
		Group group = new Group("Test", "Test group", "open", owner, userCount, date,date, userCount);
		owner.addOwningGroup(group);
		userDao.createUser(owner);*/
	}

}
