package ca.bullseye.ims.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ca.bullseye.ims.dto.LoginDTO;
import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.services.EmployeeService;

@Controller
public class HomeController {

	ApplicationContext ctx;
	@Autowired
	public EmployeeService es;

	public HomeController() {
		super();
	}

	public HomeController(EmployeeService es) {
		super();
		this.es = es;
	}

	public HomeController(ApplicationContext ctx, EmployeeService es) {
		super();
		this.es = es;
		this.ctx = ctx;
	}

	public String message;

	@GetMapping("/")

	public String home(Model model) {
		model.addAttribute("error", message);
		return "login";
	}

	@PostMapping("login")
	public String processLogin(LoginDTO loginDTOToVerify, Model model, HttpServletRequest request) {

		Employee empToVerify = new Employee(loginDTOToVerify.getEmpUserName(), loginDTOToVerify.getEmpPassword());

		Employee currentEmployee;

		if (es.doesEmployeeExist(empToVerify.getEmpUserName())) {
			currentEmployee = es.findEmployee(empToVerify.getEmpUserName());
			if (empToVerify.getEmpPassword().equals(currentEmployee.getEmpPassword())) {
				message = "";
				model.addAttribute("error", message);
				request.getSession().setAttribute("loggedIn", true);
				return "redirect:/product";
			}
			message = "Invalid username or password";
			model.addAttribute("error", message);
			return "login";
		}
		message = "Invalid username or password";
		model.addAttribute("error", message);
		return "login";

	}

	@RequestMapping("logout")
	public String processLogout(Model model, HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}

}