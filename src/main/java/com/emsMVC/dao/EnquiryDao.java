package com.emsMVC.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.emsMVC.model.Enquiry;

public interface EnquiryDao {

	public void saveOrUpdate(Enquiry enq) throws SQLException;

	public void deleteEnquiry(Enquiry enq) throws SQLException;

	public Enquiry getEnquiryById(int enqId) throws SQLException;

	public List<Enquiry> showAllEnquiries() throws SQLException;

	public List<Enquiry> getEnquiriesByUserId(int userId) throws SQLException;

	public List<Enquiry> findAllByEnquiryDate(Date date);

}