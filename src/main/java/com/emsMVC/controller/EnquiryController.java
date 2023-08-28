package com.emsMVC.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emsMVC.model.Enquiry;
import com.emsMVC.model.Users;
import com.emsMVC.service.EnquiryServices;
import com.emsMVC.service.UserServices;

@Controller
@RequestMapping("enquiry")
public class EnquiryController {

	@Autowired
	EnquiryServices enquiryServices;

	@Autowired
	UserServices userServices;

	// RESTful API methods for Retrieval operations
	// http://localhost:8080/enquiry
	// GET
	@ResponseBody
	@GetMapping("/listEnquiry")
	public List<Enquiry> list() throws SQLException {
		return enquiryServices.listAll();
	}

	// http://localhost:8080/enquiry/1014
	@ResponseBody
	@GetMapping("/getEnquiryById/{enqId}")
	public ResponseEntity<Enquiry> get(@PathVariable Integer enqId) throws SQLException {
		try {
			Enquiry enquiry = enquiryServices.getById(enqId);
			return new ResponseEntity<Enquiry>(enquiry, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Enquiry>(HttpStatus.NOT_FOUND);
		}
	}

	// http://localhost:8080/users/101
	@ResponseBody
	@GetMapping("/getEnquiryByDate")
	public ResponseEntity<List<Enquiry>> getbyTodaysDate() throws SQLException {
		try {
			List<Enquiry> enquiries = enquiryServices.findAllByEnquiryDate();
			return new ResponseEntity<List<Enquiry>>(enquiries, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Enquiry>>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@PostMapping("addEnquiry")
	public ModelAndView add(HttpServletRequest request, @RequestParam String enquiry, @RequestParam String courseName) throws SQLException {
		HttpSession session = request.getSession(false);
		Users user = userServices.getById((Integer) session.getAttribute("userId"));
		Enquiry enqb = new Enquiry();
		enqb.setCourseName(courseName);
		enqb.setEnquiry(enquiry);
		enqb.setEnquiryDate(new Date());
		enqb.setUser(user);
		List<Enquiry> enquiries = enquiryServices.listAll();
		if (enquiries == null) {
			List<Enquiry> newEnquiries = new ArrayList<Enquiry>();
			newEnquiries.add(enqb);
		} else {
			enquiries.add(enqb);
		}
		user.setEnquiry(enquiries);
		enquiryServices.save(enqb);
		userServices.save(user);
		session.setAttribute("enquiries", enquiries);
		ModelAndView mav = new ModelAndView("student-ui");
		return mav;
	}

	@GetMapping("editEnquiry")
	public String editEnquiry(Model model, @RequestParam String enqId) throws SQLException {
		int tempId = Integer.parseInt(enqId);
		Enquiry enquiry = enquiryServices.getById(tempId);
		model.addAttribute("enq", enquiry);
		return "edit-enquiry";
	}

	@PostMapping("updateEnquiry")
	public String update(HttpServletRequest request, @RequestParam Integer enqId, @RequestParam String courseName,
			@RequestParam String enquiry) throws SQLException {
		HttpSession session = request.getSession(false);
		Users user = userServices.getById((Integer) session.getAttribute("userId"));
		Enquiry enqb = new Enquiry();
		enqb.setEnqId(enqId);
		enqb.setCourseName(courseName);
		enqb.setEnquiry(enquiry);
		enqb.setEnquiryDate(new Date());
		enqb.setUser(user);
		enquiryServices.save(enqb);
		List<Enquiry> enquiries = enquiryServices.listAll();
		if (enquiries == null) {
			List<Enquiry> newEnquiries = new ArrayList<Enquiry>();
			newEnquiries.add(enqb);
		} else {
			enquiries.add(enqb);
		}
		user.setEnquiry(enquiries);
		enquiryServices.save(enqb);
		userServices.save(user);
		return "home";
	}

	@ResponseBody
	@PutMapping("/updateEnquiry/{enqId}")
	public ResponseEntity<?> update(@ModelAttribute Enquiry enquiry, @PathVariable Integer enqId) throws SQLException {
		try {
			Enquiry existingEnquiry = enquiryServices.getById(enqId);
			enquiry.setEnqId(existingEnquiry.getEnqId());
			enquiryServices.save(enquiry);
			return new ResponseEntity<Enquiry>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Enquiry>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("deleteEnquiry")
	public String deleteEnquiry(Model model, @RequestParam String enqId) throws SQLException {
		int tempId = Integer.parseInt(enqId);
		enquiryServices.delete(tempId);
		List<Enquiry> enquiries = enquiryServices.listAll();
		model.addAttribute("enquiries", enquiries);
		return "home";
	}

	@ResponseBody
	@DeleteMapping("/deleteEnquiry/{enqId}")
	public void delete(@PathVariable Integer enqId) throws SQLException {
		enquiryServices.delete(enqId);
	}
}
