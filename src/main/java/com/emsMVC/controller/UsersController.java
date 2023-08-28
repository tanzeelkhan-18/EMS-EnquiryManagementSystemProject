package com.emsMVC.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emsMVC.model.Enquiry;
import com.emsMVC.model.Users;
import com.emsMVC.service.EnquiryServices;
import com.emsMVC.service.UserServices;

@Controller
@RequestMapping("user")
public class UsersController {

	@Autowired
	UserServices userServices;

	@Autowired
	EnquiryServices enquiryService;

	@GetMapping("/myhome")
	public String myHome() {
		return "home";
	}

	@GetMapping("/addUser")
	public String addUser() {
		return "register-user";
	}

	@GetMapping("/studentUi")
	public String studentUi(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession(false);
		int userId = (Integer) session.getAttribute("userId");
		Users user = userServices.getById(userId);
		session.setAttribute("enquiries", user.getEnquiry());
		return "student-ui";
	}

	@GetMapping("/register")
	public String register() {
		return "register-user";
	}

	@GetMapping("/loginPage")
	public String login() {
		return "login";
	}

	@GetMapping("logout")
	public void logoutUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		response.sendRedirect("myhome");
	}

	@PostMapping("/authenticate")
	public String authenticateUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String userName, @RequestParam String password) throws IOException, ServletException, SQLException {
		response.setContentType("text/html");
		Users user = userServices.authenticate(userName, password);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("name", user.getName());
			session.setAttribute("userId", user.getUserId());
			List<Enquiry> enquiries = user.getEnquiry();
			session.setAttribute("enquiries", enquiries);
			return "student-ui";
		} else {
			String check = "Please Check Username or Password";
			request.setAttribute("loginError", check);
			return "login";
		}
	}

	@GetMapping("adminLoginPage")
	public String adminLoginPage() {
		return "admin-login";
	}

	@GetMapping("/adminUi")
	public String adminUi(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession(false);
		List<Enquiry> enquiries = enquiryService.listAll();
		session.setAttribute("adminEnquiries", enquiries);
		return "admin-ui";
	}

	@PostMapping("authenticateAdmin")
	public String authenticateAdmin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String userName, @RequestParam String password) throws IOException, ServletException, SQLException {
		response.setContentType("text/html");
		if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("123")) {
			HttpSession session = request.getSession();
			session.setAttribute("adminName", "Admin");
			List<Enquiry> enquiries = enquiryService.listAll();
			session.setAttribute("adminEnquiries", enquiries);
			return "admin-ui";
		} else {
			String check = "Please Enter Correct Admin Username or Password";
			request.setAttribute("loginError", check);
			return "admin-login";
		}
	}

	@GetMapping("adminLogout")
	public void logoutAdmin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		response.sendRedirect("myhome");
	}

	// RESTful API methods for Retrieval operations
	// http://localhost:8080/users
	// GET
	@ResponseBody
	@GetMapping("/listUser")
	public List<Users> list() throws SQLException {
		return userServices.listAll();
	}

	// http://localhost:8080/users/101
	@ResponseBody
	@GetMapping("/getByUserId/{userId}")
	public ResponseEntity<Users> get(@PathVariable Integer userId) throws SQLException {
		try {
			Users user = userServices.getById(userId);
			return new ResponseEntity<Users>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
	}

	// http://localhost:8080/users/101
	@ResponseBody
	@GetMapping("/listByName/{name}")
	public ResponseEntity<List<Users>> getbyName(@PathVariable String name) throws SQLException {
		try {
			List<Users> user = userServices.getByName(name);
			return new ResponseEntity<List<Users>>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Users>>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@PostMapping("/userAdd")
	public ModelAndView add(@ModelAttribute Users user) throws SQLException {
		userServices.save(user);
		ModelAndView mav = new ModelAndView("registration-success");
		return mav;
	}

	@ResponseBody
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<?> update(@RequestBody Users user, @PathVariable Integer userId) throws SQLException {
		try {
			Users existingUser = userServices.getById(userId);
			user.setUserId(existingUser.getUserId());
			userServices.save(user);
			return new ResponseEntity<Users>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@DeleteMapping("/deleteUser/{userId}")
	public void delete(@PathVariable Integer userId) throws SQLException {
		userServices.delete(userId);
	}
}
