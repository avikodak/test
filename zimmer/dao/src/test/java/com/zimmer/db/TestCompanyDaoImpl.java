package com.zimmer.db;

import java.util.Date;

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

@Component
public class TestCompanyDaoImpl {
	AbstractXmlApplicationContext context;
	static protected AbstractCompanyDao companyDao;

	@BeforeMethod
	public void setup() throws Exception {
		context = new ClassPathXmlApplicationContext("/applicationContext.xml");
	}

	@AfterMethod
	public void destroy() {
		context.destroy();
	}

	public static AbstractCompanyDao getCompanyDao() {
		return companyDao;
	}

	@Autowired
	public  void setDao(@Qualifier("companyDao") AbstractCompanyDao companyDao) {
		TestCompanyDaoImpl.companyDao = companyDao;
	}


	@BeforeClass
	public void setupClass() throws Exception {

	} 

	@Test
	public void testCompanyCreateAndGet() throws Exception {
		/*Assert.assertNotNull(companyDao);
		Integer userCount = new Integer(0);
		Date date = new Date();
		Address companyAddress = new Address("test","test1","test2","test3","test4",1234L);
		Company company = new Company("test1234","test123",userCount,date,date,true);
		company.setCompanyAddress(companyAddress);
		String companyId = companyDao.createCompany(company);
		Assert.assertNotNull(companyDao.getCompanyById(companyId));*/
	}

}
