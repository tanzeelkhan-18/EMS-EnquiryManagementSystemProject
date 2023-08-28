package com.emsMVC.service;

import java.sql.SQLException;
import java.util.List;

import com.emsMVC.model.Users;

public interface UserServices {
	public List<Users> listAll() throws SQLException;

	public void save(Users user) throws SQLException;

	public Users getById(Integer userId) throws SQLException;

	public void delete(Integer userId) throws SQLException;

	public List<Users> getByName(String name) throws SQLException;

	public Users authenticate(String userName, String password) throws SQLException;
}
