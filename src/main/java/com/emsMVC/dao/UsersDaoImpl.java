package com.emsMVC.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsMVC.model.Enquiry;
import com.emsMVC.model.Users;

@Repository
public class UsersDaoImpl implements UsersDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void saveOrUpdate(Users user) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void deleteUser(Users user) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	@Override
	@Transactional
	public Users getUserById(int userId) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		Users user = session.get(Users.class, userId);
		return user;
	}

	@Override
	@Transactional
	public List<Users> getUserByName(String name) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Users u WHERE u.name = :user_name";
		Query query = session.createQuery(hql, Users.class);
		query.setParameter("user_name", name);
		List<Users> user = query.getResultList();
		return user;
	}

	@Override
	@Transactional
	public List<Users> getAllUsers() throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Users", Users.class);
		List<Users> users = query.getResultList();
		return users;
	}

	@Override
	@Transactional
	public Users authenticate(String userName, String password) throws SQLException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM Users u WHERE u.username = :userName and u.password = :password";
			Query query = session.createQuery(hql, Users.class);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			Users user = (Users) query.getSingleResult();
			return user;
		} catch (HibernateException e) {
			return null;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<Enquiry> findEnquiryByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
