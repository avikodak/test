package com.zimmer.db.impl;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.zimmer.db.AbstractCompanyDao;
import com.zimmer.dbbeans.Company;


public class CompanyDaoImpl implements AbstractCompanyDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String createCompany(Company company) throws Exception {
		Session session = null;
		company.setCompanyId(generateCompanyId());
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(company);
			session.getTransaction().commit();
		}finally{
//			if(session != null){
//				session.close();
//			}
		}
		return company.getCompanyId();
	}

	public Company getCompanyById(String companyId) throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			return (Company)session.get(Company.class,companyId);
		}finally{
//			if(session != null){
//				session.close();
//			}
		}
	}

	private String generateCompanyId() {
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}

}
