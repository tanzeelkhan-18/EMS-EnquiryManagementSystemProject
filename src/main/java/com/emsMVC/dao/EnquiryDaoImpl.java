package com.emsMVC.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsMVC.model.Enquiry;

@Repository
public class EnquiryDaoImpl implements EnquiryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void saveOrUpdate(Enquiry enq) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(enq);
	}

	@Override
	@Transactional
	public void deleteEnquiry(Enquiry enq) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(enq);
	}

	@Override
	@Transactional
	public Enquiry getEnquiryById(int enqId) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		Enquiry enquiry = session.get(Enquiry.class, enqId);
		return enquiry;
	}

	@Override
	@Transactional
	public List<Enquiry> showAllEnquiries() throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Enquiry", Enquiry.class);
		try {
			List<Enquiry> enquiries = query.getResultList();
			return enquiries;
		} catch (PersistenceException e) {
			try {
				return null;
			} catch (HibernateException ee) {
				return null;
			}
		}
	}

	@Override
	@Transactional
	public List<Enquiry> getEnquiriesByUserId(int userId) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("selct * from Enquiry where userId=:userId", Enquiry.class);
		query.setParameter("userId", userId);
		try {
			List<Enquiry> enquiries = query.getResultList();
			return enquiries;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<Enquiry> findAllByEnquiryDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
