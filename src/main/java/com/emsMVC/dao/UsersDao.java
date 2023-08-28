package com.emsMVC.dao;

import java.sql.SQLException;
import java.util.List;

import com.emsMVC.model.Enquiry;
import com.emsMVC.model.Users;

public interface UsersDao {

	public void saveOrUpdate(Users user) throws SQLException;

	public void deleteUser(Users user) throws SQLException;

	public Users getUserById(int userId) throws SQLException;

	public List<Users> getUserByName(String name) throws SQLException;

	public List<Users> getAllUsers() throws SQLException;

	public Users authenticate(String userName, String password) throws SQLException;

	public List<Enquiry> findEnquiryByUserId(Integer userId);
}