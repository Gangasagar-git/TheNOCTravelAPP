package com.cognizant.noc.controller;

import javax.validation.Valid;

import com.cognizant.noc.entity.Admin;
import com.cognizant.noc.entity.User;
import com.cognizant.noc.repository.NOCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class NOCController {

	private final NOCRepository NOCRepository;

	private static String UPLOADED_FOLDER = "C://Mahesh//TempFolder//";

	@Autowired
	public NOCController(NOCRepository NOCRepository) {
		this.NOCRepository = NOCRepository;
	}

	@GetMapping("application")
	public String showForm(User user) {
		return "noc-application-form";
	}

	@GetMapping()
	public String home( ) {
		return "index";
	}

	@GetMapping("userList")
	public String showUserApplications(@RequestParam("phoneNo") long phoneNo,Model model) {
		List<User> users = NOCRepository.findByPhoneNo(phoneNo);
		model.addAttribute("users", users);
		return "application-list";
	}

	@GetMapping("list")
	public String showApplicationForm(Model model) {
		model.addAttribute("users", NOCRepository.findAll());
		return "application-list";
	}

	@PostMapping("add")
	public String saveApplication(@Valid User user, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "noc-application-form";
		}
		user.setStatus("New");
		user.setDiscription("");
		user.setCreateddate(new Date().toString());
		user.setStatusDate(new Date().toString());

		NOCRepository.save(user);
		redirectAttributes.addFlashAttribute("message", "NOC application submited successfully..!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:userList";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = NOCRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
		model.addAttribute("user", user);
		return "update-noc-application-form";
	}

	@PostMapping("update/{id}")
	public String updateForm(@PathVariable("id") long id, @Valid User user, BindingResult result,
							 Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-noc-application-form";
		}

		NOCRepository.save(user);
		model.addAttribute("users", NOCRepository.findAll());
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteForm(@PathVariable("id") long id, Model model) {
		User user = NOCRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
		NOCRepository.delete(user);
		model.addAttribute("users", NOCRepository.findAll());
		return "index";
	}

	@GetMapping("checkStatus")
	public String checkStatusForm( ) {
		return "check-status";
	}

	@GetMapping("checkStatus/{phoneNo}")
	public String checkStatus(@PathVariable("id") long phoneNo, Model model) {
		User user = NOCRepository.findById(phoneNo)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Phone Number:" + phoneNo));
		model.addAttribute("user", user);
		return "check-status";
	}

	@GetMapping("login")
	public String login( ) {
		return "login";
	}

	@PostMapping("viewApplications")
	public String viewApplications(Admin admin, Model model) {
		if(admin.getCity().equalsIgnoreCase("HYD") && admin.getPassword().equalsIgnoreCase("HYD123"))
			return "redirect:list";
		else
			return "redirect:login";
	}

	@RequestMapping("/upload")
	public String uploading(Model model) {
		File file = new File(UPLOADED_FOLDER);
		model.addAttribute("files", file.listFiles());
		return "upload-docs";
	}

	@RequestMapping(value = "/uploading", method = RequestMethod.POST)
	public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
		for(MultipartFile uploadedFile : uploadingFiles) {
			File file = new File(UPLOADED_FOLDER + uploadedFile.getOriginalFilename());
			uploadedFile.transferTo(file);
		}

		return "redirect:upload";
	}

	@PostMapping("filteredList")
	public String showApplicationsBasedOnCity(Long number,Model model) {
		model.addAttribute("users", NOCRepository.findByPhoneNo(number));
		return "application-list";
	}

	@GetMapping("filteredList")
	public String getApplicationFormsBasedOnCity(@RequestParam("presentCity") String city, Model model) {
		model.addAttribute("users", NOCRepository.findByPresentCity(city));
		return "application-list";
	}

	@GetMapping("/previewReport/{phoneNo}/{createddate}")
	public String UserReport(@PathVariable("phoneNo") String phoneNo, @PathVariable("createddate") String createddate, @Valid User user, BindingResult result,
							 Model model) {
		long mobileNo = Long.parseLong(phoneNo);
		List<User> users = (List<User>) NOCRepository.findByPhoneNo(mobileNo);
		List<User> users2 = new ArrayList<>();
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User next = iterator.next();
			if(next.getCreateddate().equalsIgnoreCase(createddate)) {
				model.addAttribute("user", next);
				return "previewReport";
			}
		}

		return "check-status-Approval";
	}
	@GetMapping("/submitCheckStatus")
	public String checkStatusApproval(@RequestParam("phoneNo") String phoneNo, Model model) {
		System.out.println("phone number   ::   "+phoneNo);
		long mobileNo = Long.parseLong(phoneNo);
		List<User> user = (List<User>) NOCRepository.findByPhoneNo(mobileNo);
		model.addAttribute("users", user);
		return "check-status-Approval";
	}
	@RequestMapping("/uploadNOC")
	public String uploadNOCToBookTransport(Model model) {
		File file = new File(UPLOADED_FOLDER);
		model.addAttribute("files", file.listFiles());
		return "upload-docs";
	}
}
