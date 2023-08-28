package com.emsMVC.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emsMVC.dao.UsersDaoImpl;
import com.emsMVC.model.Users;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UsersDaoImpl userDao;

	@Override
	public List<Users> listAll() throws SQLException {
		return userDao.getAllUsers();
	}

	@Override
	public void save(Users user) throws SQLException {
		userDao.saveOrUpdate(user);
	}

	@Override
	public Users getById(Integer userId) throws SQLException {
		return userDao.getUserById(userId);
	}

	@Override
	public void delete(Integer userId) throws SQLException {
		Users user = userDao.getUserById(userId);
		userDao.deleteUser(user);
	}

	@Override
	public List<Users> getByName(String name) throws SQLException {
		return userDao.getUserByName(name);
	}

	@Override
	public Users authenticate(String userName, String password) throws SQLException {
		return userDao.authenticate(userName, password);
	}

}
