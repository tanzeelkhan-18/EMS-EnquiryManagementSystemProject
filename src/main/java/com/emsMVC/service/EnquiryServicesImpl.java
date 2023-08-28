package com.emsMVC.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emsMVC.dao.EnquiryDaoImpl;
import com.emsMVC.dao.UsersDaoImpl;
import com.emsMVC.model.Enquiry;

@Service
public class EnquiryServicesImpl implements EnquiryServices {

	@Autowired
	private EnquiryDaoImpl enquiryDao;

	@Autowired
	private UsersDaoImpl userDao;

	@Override
	public List<Enquiry> listAll() throws SQLException {
		return enquiryDao.showAllEnquiries();
	}

	@Override
	public void save(Enquiry enquiry) throws SQLException {
		enquiryDao.saveOrUpdate(enquiry);
	}

	@Override
	public Enquiry getById(Integer enqId) throws SQLException {
		return enquiryDao.getEnquiryById(enqId);
	}

	@Override
	public void delete(Integer enqId) throws SQLException {
		Enquiry enq = enquiryDao.getEnquiryById(enqId);
		enquiryDao.deleteEnquiry(enq);
	}

	@Override
	public List<Enquiry> findAllByEnquiryDate() throws SQLException {
		return enquiryDao.findAllByEnquiryDate(
		          new Date());
	}

	@Override
	public List<Enquiry> findByUserId(Integer userId) throws SQLException {
		return userDao.findEnquiryByUserId(userId);
	}

}
