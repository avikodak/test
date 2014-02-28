package com.zimmer.db.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.zimmer.db.AbstractTagDao;
import com.zimmer.dbbeans.Tag;
import com.zimmer.dbbeans.TagEventMap;
import com.zimmer.dbbeans.TagFeedMap;
import com.zimmer.dbbeans.TagTaskMap;

public class TagDaoImpl implements AbstractTagDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createTag(Tag tag) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(tag);
			session.getTransaction().commit();
		}finally{
			if (session != null) {
				session.close();
			}
		}		
	}

	public void createLinkTagEvent(TagEventMap tagEventMap) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.save(tagEventMap);
		}finally{
			if (session != null) {
				session.close();
			}
		}
	}

	public void createLinkTagTask(TagTaskMap tagTaskMap) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.save(tagTaskMap);
		}finally{
			if (session != null) {
				session.close();
			}
		}	
	}

	public void createLinkTagFeed(TagFeedMap tagFeedMap) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.save(tagFeedMap);
		}finally{
			if (session != null) {
				session.close();
			}
		}
	}
	
}
