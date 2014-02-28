package com.zimmer.db.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.zimmer.db.AbstractUserDao;
import com.zimmer.dbbeans.User;

@Repository
@Qualifier("userDao")
public class UserDaoImpl implements AbstractUserDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(@Qualifier("dbSessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String createUser(User user) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(user);
			session.getTransaction().commit();
			return user.getUserId();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public User getUserById(String userId) throws Exception {
		Session session = null;

		session = sessionFactory.openSession();
		return (User) session.get(User.class, userId);

	}

	public User getUserByUsername(String userName) throws Exception {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", userName));
		return (User) criteria.uniqueResult();
	}

	public String sayHello() throws Exception {
		return "Hello World 1";
	}

	
}
