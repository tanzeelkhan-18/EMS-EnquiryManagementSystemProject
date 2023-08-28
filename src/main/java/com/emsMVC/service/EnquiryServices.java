package com.emsMVC.service;

import java.sql.SQLException;
import java.util.List;

import com.emsMVC.model.Enquiry;

public interface EnquiryServices {
	public List<Enquiry> listAll() throws SQLException;
	public void save(Enquiry enquiry) throws SQLException;
	public Enquiry getById(Integer enqId) throws SQLException;
	public void delete(Integer enqId) throws SQLException;
	public List<Enquiry> findAllByEnquiryDate() throws SQLException;
	public List<Enquiry> findByUserId(Integer userId) throws SQLException;
}
