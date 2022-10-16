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
import ca.bullseye.ims.model.Employee;

import ca.bullseye.ims.services.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

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

	// display list of employee
	@RequestMapping(value = "/employee")
	public String viewEmployeeList(Model model) {
		List<Employee> employeeList = employeeService.getAllEmployees();
		model.addAttribute("employeeList", employeeList);

		return "employeelist";
	}

	// show new or create employee page
	@RequestMapping(value = "/employee/create", method = RequestMethod.GET)
	public String newEmployee(Model model) {

		// create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute(employee);

		model.addAttribute("empDepartment", empDepartment);
		model.addAttribute("empJobRole", empJobRole);

		return "employee_new";
	}

	// save employee to database with error validation
	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public String addNewEmployee(@ModelAttribute @Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			return "employee_new";
		}
		employeeService.addNewEmployee(employee);
		return "redirect:/employee";
	}

	// creating a get mapping that retrieves the detail of a specific employee
	@GetMapping(path = "/employee/edit/{empId}")
	public String editEmployee(@PathVariable(name = "empId") Long empId, Model model) {
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(empId);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		model.addAttribute("empDepartment", empDepartment);
		model.addAttribute("empJobRole", empJobRole);
		return "/employee_edit";
	}

	// save updated employee record
	@RequestMapping(value="/employee/update", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute @Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			return "employee_edit";
		}
		employeeService.updateEmployee(employee);
		return "redirect:/employee";
	}

	// creating a delete mapping that deletes a specific employee
	@RequestMapping(value = "/employee/delete/{empId}", method = { RequestMethod.DELETE, RequestMethod.GET })
	private String deleteEmployee(@PathVariable(name = "empId") Long empId) {
		// call delete employee method
		employeeService.deleteEmployee(empId);
		return "redirect:/employee";
	}

	/*
	 * // pagination and sorting
	 * 
	 * @GetMapping(path = "/page/{pageNo}") public String
	 * findPaginated(@PathVariable(value = "pageNo") int
	 * pageNo, @RequestParam("sortFieldEmp") String sortFieldEmp,
	 * 
	 * @RequestParam("sortDirectionEmp") String sortDirectionEmp, Model model) { int
	 * pageSize = 6;
	 * 
	 * Page<Employee> pageEmp = employeeService.findPaginated(pageNo, pageSize,
	 * sortFieldEmp, sortDirectionEmp); List<Employee> employeeList =
	 * pageEmp.getContent();
	 * 
	 * model.addAttribute("currentPage", pageNo); model.addAttribute("totalPages",
	 * pageEmp.getTotalPages()); model.addAttribute("totalItems",
	 * pageEmp.getTotalElements());
	 * 
	 * model.addAttribute("sortFieldEmp", sortFieldEmp);
	 * model.addAttribute("sortDirection", sortDirectionEmp);
	 * model.addAttribute("reverseSortDirection", sortDirectionEmp.equals("asc") ?
	 * "desc" : "asc");
	 * 
	 * model.addAttribute("employeeList", employeeList);
	 * 
	 * return "employeelist"; }
	 */

}
