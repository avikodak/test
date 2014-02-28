package com.zimmer.db.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.zimmer.db.AbstractGroupDao;
import com.zimmer.dbbeans.Group;
import com.zimmer.dbbeans.GroupMemberLink;

public class GroupDaoImpl implements AbstractGroupDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createGroup(Group group) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(group);
			session.getTransaction().commit();
		}finally{

		}
	}

	public void createGroupUserLink(GroupMemberLink groupMemberLink) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.save(groupMemberLink);
		}finally{
			if (session != null) {
				session.close();
			}
		}
	}
	
}
