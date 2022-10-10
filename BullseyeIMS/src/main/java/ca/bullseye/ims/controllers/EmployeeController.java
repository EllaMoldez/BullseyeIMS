package ca.bullseye.ims.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.bullseye.ims.exceptions.ProductNotFoundException;
import ca.bullseye.ims.model.Employee;

import ca.bullseye.ims.services.EmployeeService;

public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping(path = "/employee")
	public String viewEmployeeList(Model model) {
		List<Employee> employeeList = employeeService.getAllEmployees();
		model.addAttribute("employeeList", employeeList);

		return "employeelist";
	}
	
	@RequestMapping("/employee/add")
	public String newEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute(employee);

		return "employee_new";
	}
	
	@RequestMapping(value = "/employee/save", method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employees, BindingResult result, @ModelAttribute("employee") Employee employee) {
		if(result.hasErrors()) {
			return "employee_new";
		}
		employeeService.saveEmployee(employee);
		return "/employee";
	}
	
	@RequestMapping(value = "/employee/edit/{empId}",  method = RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable(name = "empId") Long empId, @Valid Employee employees, BindingResult result) throws ProductNotFoundException {
		ModelAndView mav = new ModelAndView("employee_edit");
		Employee employee = employeeService.getEmployeeById(empId);
		mav.addObject("employee", employee);
		return mav;
	}
	
	@RequestMapping(value = "/employee/delete/{empId}")
	private String deleteEmployee(@PathVariable(name = "empId") Long empId) {
		employeeService.deleteEmployee(empId);
		return "/employee";
	}
	
}
