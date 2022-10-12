package ca.bullseye.ims.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.bullseye.ims.exceptions.RecordNotFoundException;
import ca.bullseye.ims.model.Employee;

import ca.bullseye.ims.services.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	private static List<String> empDepartment;
	static {
		empDepartment = new ArrayList<>();
		empDepartment.add("Regional");
		empDepartment.add("Finance");
		empDepartment.add("Warehouse");
		empDepartment.add("Store");
		empDepartment.add("Transportation");
	}
	
	private static List<String> empJobRole;
	static {
		empJobRole = new ArrayList<>();
		empJobRole.add("Manager");
		empJobRole.add("Asst. Manager");
		empJobRole.add("Staff");
		empJobRole.add("Delivery Driver");
	}
	
	@GetMapping(path = "/employee")
	public String viewEmployeeList(Model model) {
		List<Employee> employeeList = employeeService.getAllEmployees();
		model.addAttribute("employeeList", employeeList);

		return "employeelist";
	}
	
	@RequestMapping(path = "/employee/add")
	public String newEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute(employee);
		
		model.addAttribute("empDepartment", empDepartment);
		model.addAttribute("empJobRole", empJobRole);

		return "employee_new";
	}
	
	@RequestMapping(path = "/employee/save", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		
		employeeService.saveEmployee(employee);

		
		return "redirect:/employee";
	}
	
	@RequestMapping(path = "/employee/edit/{empId}",  method = RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable(name = "empId") Long empId, @Valid Employee employees, BindingResult result) throws RecordNotFoundException {
		ModelAndView mav = new ModelAndView("employee_edit");
		Employee employee = employeeService.getEmployeeById(empId);
		mav.addObject("employee", employee);
		return mav;
	}
	
	@RequestMapping(value = "/employee/delete/{empId}")
	private String deleteEmployee(@PathVariable(name = "empId") Long empId) {
		employeeService.deleteEmployee(empId);
		return "redirect:/employee";
	}
	
}
